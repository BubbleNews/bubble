package us.bubblenews.bubbleserver.clustering;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.List;
import java.util.Set;

public interface ClusteringAlgorithm {
    public List<Set<Integer>> getClusters(SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph);
}
