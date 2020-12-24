package us.bubblenews.bubbleserver.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.bubblenews.bubbleserver.api.request.Bulk;
import us.bubblenews.bubbleserver.api.request.ScrapedArticle;
import us.bubblenews.bubbleserver.model.Article;
import us.bubblenews.bubbleserver.service.ArticleService;
import us.bubblenews.bubbleserver.service.NewsSourceService;
import us.bubblenews.bubbleserver.service.TextProcessingService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private NewsSourceService newsSourceService;

    @Autowired
    private TextProcessingService textProcessingService;

    @PostMapping
    public Article addArticle(@RequestBody ScrapedArticle scrapedArticle) {
        Article article = new Article();
        article.setTitle(scrapedArticle.getTitle());
        article.setUrl(scrapedArticle.getUrl());
        article.setTimePublished(scrapedArticle.getTimePublished());
        article.setSource(newsSourceService.getByNameOrCreate(scrapedArticle.getSourceName()));
        Map<String, Integer> wordFrequencyMap = textProcessingService
                .getWordFrequencyMap(scrapedArticle.getRawContent(), " ", false, false);
        article.setWordFrequencyMap(wordFrequencyMap);
        return articleService.saveArticle(article);
    }

    @GetMapping(path="/all")
    public Iterable<Article> getAllArticles() {
        List<Article> articles = articleService.findAll();
        return articles;
    }
}
