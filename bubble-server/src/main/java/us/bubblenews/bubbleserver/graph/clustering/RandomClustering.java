package us.bubblenews.bubbleserver.graph.clustering;

import com.google.common.collect.Lists;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class RandomClustering<E extends DefaultWeightedEdge> implements ClusteringAlgorithm<E> {
    private int desiredNumberOfClusters;

    public RandomClustering(int desiredNumberOfClusters) {
        this.desiredNumberOfClusters = desiredNumberOfClusters;
    }

    @Override
    public List<Cluster<E>> getClusters(SimpleWeightedGraph<Integer, E> graph) {
        List<Integer> vertices = new ArrayList<>(graph.vertexSet());
        Collections.shuffle(vertices);
        return Lists.partition(vertices, desiredNumberOfClusters)
                .stream()
                .map(verticesInCluster -> new Cluster<E>(graph, new HashSet<>(verticesInCluster)))
                .collect(Collectors.toList());
    }
}
