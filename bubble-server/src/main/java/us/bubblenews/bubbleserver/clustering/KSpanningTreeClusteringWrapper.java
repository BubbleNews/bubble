package us.bubblenews.bubbleserver.clustering;

import org.jgrapht.alg.clustering.KSpanningTreeClustering;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.List;
import java.util.Set;

public class KSpanningTreeClusteringWrapper implements ClusteringAlgorithm {

    private int desiredNumberOfClusters;

    public KSpanningTreeClusteringWrapper(int desiredNumberOfClusters) {
        this.desiredNumberOfClusters = desiredNumberOfClusters;
    }

    @Override
    public List<Set<Integer>> getClusters(SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph) {
        KSpanningTreeClustering<Integer, DefaultWeightedEdge> algo = new KSpanningTreeClustering<>(graph, desiredNumberOfClusters);
        return algo.getClustering().getClusters();
    }
}
