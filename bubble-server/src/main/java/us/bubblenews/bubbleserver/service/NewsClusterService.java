package us.bubblenews.bubbleserver.service;

import us.bubblenews.bubbleserver.graph.clustering.ClusteringAlgorithm;
import us.bubblenews.bubbleserver.graph.similarity.EdgeWeightCalculator;
import us.bubblenews.bubbleserver.model.Article;
import us.bubblenews.bubbleserver.model.NewsCluster;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface NewsClusterService {
    public List<NewsCluster> makeClustersFromArticles(Collection<Article> articles, EdgeWeightCalculator<Article> edgeWeightCalculator,
                                                      ClusteringAlgorithm algorithm, double edgeWeightThreshold);

    public List<NewsCluster> saveClusters(Collection<NewsCluster> newsClusters);

    public List<NewsCluster> getClustersForDate(Date date);
}
