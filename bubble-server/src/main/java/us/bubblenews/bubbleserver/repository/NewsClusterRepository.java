package us.bubblenews.bubbleserver.repository;

import org.springframework.data.repository.CrudRepository;
import us.bubblenews.bubbleserver.model.NewsCluster;

import java.util.Date;
import java.util.List;

public interface NewsClusterRepository extends CrudRepository<NewsCluster, Integer> {
    public List<NewsCluster> findNewsClusterByDate(Date date);
}
