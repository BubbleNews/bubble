package us.bubblenews.bubbleserver.repository;

import org.springframework.data.repository.CrudRepository;
import us.bubblenews.bubbleserver.model.NewsSource;

import java.util.List;

public interface NewsSourceRepository extends CrudRepository<NewsSource, Integer> {

    public List<NewsSource> findNewsSourcesByName(String name);
}
