package us.bubblenews.bubbleserver.graph.clustering;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.List;

public interface ClusteringAlgorithm<E extends DefaultWeightedEdge> {
    public List<Cluster> getClusters(SimpleWeightedGraph<Integer, E> graph);
}
