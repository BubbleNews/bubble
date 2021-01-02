package us.bubblenews.bubbleserver.graph.similarity;

public interface EdgeWeightCalculator<V, E> {
    public E calculateEdge(V v1, V v2);
}
