package us.bubblenews.bubbleserver.repository;

import org.springframework.data.repository.CrudRepository;
import us.bubblenews.bubbleserver.model.Article;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
    Iterable<Article> findArticlesBySourceId(Integer sourceId);
}
