package us.bubblenews.bubbleserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.bubblenews.bubbleserver.model.Article;
import us.bubblenews.bubbleserver.repository.ArticleRepository;
import us.bubblenews.bubbleserver.service.ArticleService;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl extends AbstractModelServiceImpl<Article> implements ArticleService {

    @Autowired
    private ArticleRepository repository;

    @Override
    public Article saveArticle(Article article) {
        return repository.save(article);
    }

    @Override
    public List<Article> findArticlesByDate(Date date) {
        return repository.findArticlesByDate(date);
    }

    @Override
    public List<Article> findAll() {
        return iterableToList(repository.findAll());
    }
}
