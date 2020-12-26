package us.bubblenews.bubbleserver.service;

import us.bubblenews.bubbleserver.clustering.Clusterer;
import us.bubblenews.bubbleserver.graph.Cluster;
import us.bubblenews.bubbleserver.model.Article;
import us.bubblenews.bubbleserver.model.NewsCluster;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface NewsClusterService {
    public List<NewsCluster> saveClusters(List<Cluster<Article>> clusters);

    public Set<Cluster<Article>> makeClusters(Collection<Article> articles, Clusterer<Article> clusterer);
}
