package us.bubblenews.bubbleapi.repository;

import org.springframework.data.repository.CrudRepository;
import us.bubblenews.bubbleapi.model.Article;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
}
