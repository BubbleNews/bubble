package us.bubblenews.bubbleserver.graph.clustering;

import org.jgrapht.graph.SimpleWeightedGraph;
import us.bubblenews.bubbleserver.graph.similarity.SimpleWeighted;

import java.util.List;
import java.util.Set;

public interface ClusteringAlgorithm<E extends SimpleWeighted> {
    public List<Set<Integer>> getClusters(SimpleWeightedGraph<Integer, E> graph);
}
