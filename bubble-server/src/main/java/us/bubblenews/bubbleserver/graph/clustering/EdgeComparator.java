package us.bubblenews.bubbleserver.graph.clustering;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.Comparator;

public class EdgeComparator<E extends DefaultWeightedEdge> implements Comparator<E> {
    private SimpleWeightedGraph<Integer, E> graph;

    public EdgeComparator(SimpleWeightedGraph<Integer, E> graph) {
        this.graph = graph;
    }

    @Override
    public int compare(E e1, E e2) {
        return Double.compare(graph.getEdgeWeight(e1), graph.getEdgeWeight(e2));
    }
}
