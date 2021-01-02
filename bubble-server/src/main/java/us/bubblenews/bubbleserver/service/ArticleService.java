package us.bubblenews.bubbleserver.service;

import us.bubblenews.bubbleserver.model.Article;

import java.util.Date;
import java.util.List;

public interface ArticleService {
    public Article saveArticle(Article article);

    public List<Article> findArticlesByDate(Date date);

    public List<Article> findAll();
}
