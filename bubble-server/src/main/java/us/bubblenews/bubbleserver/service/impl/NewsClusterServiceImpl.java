package us.bubblenews.bubbleserver.service.impl;

import org.springframework.stereotype.Service;
import us.bubblenews.bubbleserver.clustering.Clusterer;
import us.bubblenews.bubbleserver.graph.Cluster;
import us.bubblenews.bubbleserver.model.Article;
import us.bubblenews.bubbleserver.model.NewsCluster;
import us.bubblenews.bubbleserver.service.NewsClusterService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class NewsClusterServiceImpl implements NewsClusterService {
    @Override
    public List<NewsCluster> saveClusters(List<Cluster<Article>> clusters) {
        return null;
    }

    @Override
    public Set<Cluster<Article>> makeClusters(Collection<Article> articles, Clusterer<Article> clusterer) {
        return null;
    }
}
