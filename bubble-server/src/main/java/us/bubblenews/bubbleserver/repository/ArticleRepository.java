package us.bubblenews.bubbleserver.repository;

import org.springframework.data.repository.CrudRepository;
import us.bubblenews.bubbleserver.model.Article;

import java.util.Date;
import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
    List<Article> findArticlesBySourceId(Integer sourceId);

    List<Article> findArticlesByTimePublished(Date date);
}
