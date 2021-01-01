package us.bubblenews.bubbleserver.clustering;

public interface EdgeWeightCalculator<T> {
    public double calculateEdgeWeight(T node1, T node2);
}
