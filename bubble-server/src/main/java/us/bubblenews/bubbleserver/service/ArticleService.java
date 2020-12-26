package us.bubblenews.bubbleserver.service;

import us.bubblenews.bubbleserver.model.Article;

import java.util.List;

public interface ArticleService {
    public Article saveArticle(Article article);

    public List<Article> findAll();
}
