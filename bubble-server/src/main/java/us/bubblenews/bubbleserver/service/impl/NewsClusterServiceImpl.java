package us.bubblenews.bubbleserver.service.impl;

import org.jgrapht.graph.SimpleWeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.bubblenews.bubbleserver.graph.clustering.Cluster;
import us.bubblenews.bubbleserver.graph.clustering.ClusteringAlgorithm;
import us.bubblenews.bubbleserver.graph.similarity.ArticleSimilarity;
import us.bubblenews.bubbleserver.graph.similarity.EdgeBuilder;
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
    public List<NewsCluster> makeClustersFromArticles(Collection<Article> articles, EdgeBuilder<Article, ArticleSimilarity> edgeBuilder,
                                                      ClusteringAlgorithm<ArticleSimilarity> algorithm) {
        SimpleWeightedGraph<Integer, ArticleSimilarity> graph = makeGraph(articles, edgeBuilder);

        Map<Integer, Article> articleMap = new HashMap<>();
        articles.stream().forEach(article -> articleMap.put(article.getId(), article));
        List<NewsCluster> clusters = new ArrayList<>();
        for (Cluster<ArticleSimilarity> cluster : algorithm.getClusters(graph)) {
            Set<Article> articlesInCluster = cluster.getVertices()
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

    @Override
    public List<NewsCluster> getAllClusters() {
        return iterableToList(repository.findAll());
    }

    private SimpleWeightedGraph<Integer, ArticleSimilarity> makeGraph(Collection<Article> articles,
                                                                      EdgeBuilder<Article, ArticleSimilarity> edgeBuilder) {
        SimpleWeightedGraph<Integer, ArticleSimilarity> graph = new SimpleWeightedGraph<>(ArticleSimilarity.class);
        for (Article article1 : articles) {
            graph.addVertex(article1.getId());
            for (Article article2 : articles) {
                if (!graph.containsVertex(article2.getId())) {
                    graph.addVertex(article2.getId());
                }
                if (article1.hashCode() < article2.hashCode()) {
                    ArticleSimilarity edge = edgeBuilder.build(article1, article2);
                    graph.addEdge(article1.getId(), article2.getId(), edge);
                    graph.setEdgeWeight(edge, edge.getWeight());
                }
            }
        }
        return graph;
    }
}
