package us.bubblenews.bubbleserver.service.impl;

import org.jgrapht.Graph;
import org.jgrapht.alg.clustering.KSpanningTreeClustering;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.bubblenews.bubbleserver.clustering.ClusteringAlgorithm;
import us.bubblenews.bubbleserver.clustering.EdgeWeightCalculator;
import us.bubblenews.bubbleserver.model.Article;
import us.bubblenews.bubbleserver.model.NewsCluster;
import us.bubblenews.bubbleserver.repository.NewsClusterRepository;
import us.bubblenews.bubbleserver.service.NewsClusterService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NewsClusterServiceImpl extends AbstractModelServiceImpl<NewsCluster> implements NewsClusterService {

    @Autowired
    private NewsClusterRepository repository;

    @Override
    public List<NewsCluster> makeClustersFromArticles(Collection<Article> articles, EdgeWeightCalculator<Article> edgeWeightCalculator,
                                                      ClusteringAlgorithm algorithm, double edgeWeightThreshold) {
        SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = makeGraph(articles, edgeWeightCalculator, edgeWeightThreshold);

        Map<Integer, Article> articleMap = new HashMap<>();
        articles.stream().forEach(article -> articleMap.put(article.getId(), article));
        List<NewsCluster> clusters = new ArrayList<>();
        for (Set<Integer> articleIdsInCluster : algorithm.getClusters(graph)) {
            Set<Article> articlesInCluster = articleIdsInCluster
                    .stream()
                    .map(id -> articleMap.get(id))
                    .collect(Collectors.toSet());
            clusters.add(new NewsCluster(articlesInCluster));
        }
        return clusters;
    }

    @Override
    public List<NewsCluster> saveClusters(Collection<NewsCluster> newsClusters) {
        return iterableToList(repository.saveAll(newsClusters));
    }

    @Override
    public List<NewsCluster> getClustersForDate(Date date) {
        // TODO: implement get clusters for date
        return new ArrayList<>();
    }

    private SimpleWeightedGraph<Integer, DefaultWeightedEdge> makeGraph(Collection<Article> articles,
                                                          EdgeWeightCalculator<Article> edgeWeightCalculator,
                                                          double edgeWeightThreshold) {
        SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        for (Article article1 : articles) {
            graph.addVertex(article1.getId());
            for (Article article2 : articles) {
                if (article1.hashCode() < article2.hashCode()) {
                    double similarity = edgeWeightCalculator.calculateEdgeWeight(article1, article2);
                    if (similarity >= edgeWeightThreshold) {
                        DefaultWeightedEdge edge = graph.addEdge(article1.getId(), article2.getId());
                        graph.setEdgeWeight(edge, similarity);
                    }
                }
            }
        }
        return graph;
    }
}
