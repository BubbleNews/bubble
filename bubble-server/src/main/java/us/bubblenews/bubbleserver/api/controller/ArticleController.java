package us.bubblenews.bubbleserver.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import us.bubblenews.bubbleserver.api.request.Bulk;
import us.bubblenews.bubbleserver.api.request.ScrapedArticle;
import us.bubblenews.bubbleserver.model.Article;
import us.bubblenews.bubbleserver.service.ArticleService;
import us.bubblenews.bubbleserver.service.NewsSourceService;
import us.bubblenews.bubbleserver.service.TextProcessingService;
import us.bubblenews.bubbleserver.service.VocabService;

import java.util.*;

@RestController
@RequestMapping(path="/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private NewsSourceService newsSourceService;

    @Autowired
    private VocabService vocabService;

    @Qualifier("Simple")
    @Autowired
    private TextProcessingService textProcessingService;

    @PostMapping
    public Article addArticle(@RequestBody ScrapedArticle scrapedArticle) {
        return addArticles(Arrays.asList(scrapedArticle)).get(0);
    }

    @PostMapping("/bulk")
    public List<Article> addArticlesBulk(@RequestBody Bulk<ScrapedArticle> body) {
        return addArticles(body.getItems());
    }

    @GetMapping(path="/all")
    public List<Article> getAllArticles() {
        List<Article> articles = articleService.findAll();
        return articles;
    }

    private List<Article> addArticles(List<ScrapedArticle> scrapedArticles) {
        List<Article> articles = new ArrayList<>();
        Map<String, Integer> wordToNumberOfArticles = new HashMap<>();
        for (ScrapedArticle scrapedArticle : scrapedArticles) {
            Article article = new Article();
            article.setTitle(scrapedArticle.getTitle());
            article.setUrl(scrapedArticle.getUrl());
            article.setTimePublished(scrapedArticle.getTimePublished());
            article.setSource(newsSourceService.getByNameOrCreate(scrapedArticle.getSourceName()));
            Map<String, Integer> wordFrequencyInArticle = textProcessingService
                    .getTermFrequencyMap(scrapedArticle.getRawContent(), true, true);
            for (String word : wordFrequencyInArticle.keySet()) {
                wordToNumberOfArticles.put(word, wordToNumberOfArticles.getOrDefault(word, 0) + 1);
            }
            article.setWordFrequencyMap(wordFrequencyInArticle);
            articles.add(articleService.saveArticle(article));
        }
        vocabService.addToVocabArticleFrequency(wordToNumberOfArticles);
        return articles;
    }
}
