package us.bubblenews.bubbleserver.graph.clustering;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.List;
import java.util.Set;

public interface ClusteringAlgorithm<E extends DefaultWeightedEdge> {
    public List<Set<Integer>> getClusters(SimpleWeightedGraph<Integer, E> graph);
}
