package us.bubblenews.bubbleserver.service.impl;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.bubblenews.bubbleserver.graph.GraphBuilder;
import us.bubblenews.bubbleserver.graph.clustering.ClusteringAlgorithm;
import us.bubblenews.bubbleserver.graph.similarity.EdgeWeightCalculator;
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
        GraphBuilder<Article> graphBuilder = new GraphBuilder<Article>()
                .setObjects(articles)
                .setEdgeWeightCalculator(edgeWeightCalculator)
                .setEdgeWeightThreshold(edgeWeightThreshold);
        SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = graphBuilder.build();

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
}
