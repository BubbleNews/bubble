package us.bubblenews.bubbleserver.repository;

import org.springframework.data.repository.CrudRepository;
import us.bubblenews.bubbleserver.model.NewsCluster;

public interface NewsClusterRepository extends CrudRepository<NewsCluster, Integer> {
}
