package us.bubblenews.bubbleserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.bubblenews.bubbleserver.model.Article;
import us.bubblenews.bubbleserver.repository.ArticleRepository;
import us.bubblenews.bubbleserver.service.ArticleService;
import us.bubblenews.bubbleserver.service.NewsSourceService;
import us.bubblenews.bubbleserver.service.VocabService;

import java.util.List;

@Service
public class ArticleServiceImpl extends AbstractModelServiceImpl<Article> implements ArticleService {

    @Autowired
    private ArticleRepository repository;

    @Autowired
    private VocabService vocabService;

    @Override
    public Article saveArticle(Article article) {
        for (String word : article.getWordFrequencyMap().keySet()) {
            vocabService.addToVocabCount(word, article.getWordFrequencyMap().get(word));
        }
        return repository.save(article);
    }

    @Override
    public List<Article> findAll() {
        return iterableToList(repository.findAll());
    }
}
