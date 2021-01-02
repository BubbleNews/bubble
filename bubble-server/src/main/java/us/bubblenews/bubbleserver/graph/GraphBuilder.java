package us.bubblenews.bubbleserver.graph;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import us.bubblenews.bubbleserver.graph.similarity.EdgeWeightCalculator;
import us.bubblenews.bubbleserver.model.Identifiable;

import java.util.Collection;

public class GraphBuilder<V extends Identifiable> {
    private Collection<V> objects;
    private EdgeWeightCalculator<V> edgeWeightCalculator;
    private double edgeWeightThreshold;

    public GraphBuilder<V> setObjects(Collection<V> objects) {
        this.objects = objects;
        return this;
    }

    public GraphBuilder<V> setEdgeWeightCalculator(EdgeWeightCalculator<V> edgeWeightCalculator) {
        this.edgeWeightCalculator = edgeWeightCalculator;
        return this;
    }

    public GraphBuilder<V> setEdgeWeightThreshold(double edgeWeightThreshold) {
        this.edgeWeightThreshold = edgeWeightThreshold;
        return this;
    }

    public SimpleWeightedGraph<Integer, DefaultWeightedEdge> build() {
        SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        for (V object1 : objects) {
            graph.addVertex(object1.getId());
            for (V object2 : objects) {
                if (object1.hashCode() < object2.hashCode()) {
                    double similarity = edgeWeightCalculator.calculateEdgeWeight(object1, object2);
                    if (similarity >= edgeWeightThreshold) {
                        DefaultWeightedEdge edge = graph.addEdge(object1.getId(), object2.getId());
                        graph.setEdgeWeight(edge, similarity);
                    }
                }
            }
        }
        return graph;
    }
}
