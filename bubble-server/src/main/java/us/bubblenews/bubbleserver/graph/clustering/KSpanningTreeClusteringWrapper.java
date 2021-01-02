package us.bubblenews.bubbleserver.graph.clustering;

import org.jgrapht.alg.clustering.KSpanningTreeClustering;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;


import java.util.List;
import java.util.Set;

public class KSpanningTreeClusteringWrapper<E extends DefaultWeightedEdge> implements ClusteringAlgorithm<E> {

    private int desiredNumberOfClusters;

    public KSpanningTreeClusteringWrapper(int desiredNumberOfClusters) {
        this.desiredNumberOfClusters = desiredNumberOfClusters;
    }

    @Override
    public List<Set<Integer>> getClusters(SimpleWeightedGraph<Integer, E> graph) {
        KSpanningTreeClustering<Integer, E> algo = new KSpanningTreeClustering<>(graph, desiredNumberOfClusters);
        return algo.getClustering().getClusters();
    }
}
