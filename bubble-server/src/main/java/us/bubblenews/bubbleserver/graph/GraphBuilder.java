package us.bubblenews.bubbleserver.graph;

import org.jgrapht.graph.SimpleWeightedGraph;
import us.bubblenews.bubbleserver.graph.similarity.EdgeBuilder;
import us.bubblenews.bubbleserver.graph.similarity.SimpleWeighted;
import us.bubblenews.bubbleserver.model.Identifiable;

import java.util.Collection;

public class GraphBuilder<V extends Identifiable, E extends SimpleWeighted> {
    private Collection<V> objects;
    private EdgeBuilder<V, E> edgeBuilder;
    private double edgeWeightThreshold;
    private Class<E> edgeClass;

    public GraphBuilder<V, E> setObjects(Collection<V> objects) {
        this.objects = objects;
        return this;
    }

    public GraphBuilder<V, E> setEdgeBuilder(EdgeBuilder<V, E> edgeBuilder) {
        this.edgeBuilder = edgeBuilder;
        return this;
    }

    public GraphBuilder<V, E> setEdgeWeightThreshold(double edgeWeightThreshold) {
        this.edgeWeightThreshold = edgeWeightThreshold;
        return this;
    }

    public GraphBuilder<V, E> setEdgeClass(Class<E> edgeClass) {
        this.edgeClass = edgeClass;
        return this;
    }

    public SimpleWeightedGraph<Integer, E> build() {
        SimpleWeightedGraph<Integer, E> graph = new SimpleWeightedGraph<>(edgeClass);
        for (V object1 : objects) {
            graph.addVertex(object1.getId());
            for (V object2 : objects) {
                if (object1.hashCode() < object2.hashCode()) {
                    E edge = edgeBuilder.build(object1, object2);
                    if (edge.getSimpleWeight() >= edgeWeightThreshold) {
                        graph.addEdge(object1.getId(), object2.getId(), edge);
                        graph.setEdgeWeight(edge, edge.getSimpleWeight());
                    }
                }
            }
        }
        return graph;
    }
}
