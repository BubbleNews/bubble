package us.bubblenews.bubbleserver.service;

import us.bubblenews.bubbleserver.graph.Cluster;
import us.bubblenews.bubbleserver.graph.Graph;
import us.bubblenews.bubbleserver.model.Article;

import java.util.Set;

public interface ClusteringService {
    public Set<Cluster<Article>> createClusters(Graph<Article> articleGraph);
}
