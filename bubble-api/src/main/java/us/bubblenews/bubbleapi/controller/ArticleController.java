package us.bubblenews.bubbleapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.bubblenews.bubbleapi.model.Article;
import us.bubblenews.bubbleapi.repository.ArticleRepository;

@RestController
@RequestMapping(path="/article")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping(path="/add")
    public Article addNewArticle(@RequestParam String title) {
        Article article = new Article();
        article.setTitle(title);
        return articleRepository.save(article);
    }

    @GetMapping(path="/all")
    public Iterable<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}