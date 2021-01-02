package us.bubblenews.bubbleserver.graph.similarity;

public interface EdgeWeightCalculator<T> {
    public double calculateEdgeWeight(T node1, T node2);
}
