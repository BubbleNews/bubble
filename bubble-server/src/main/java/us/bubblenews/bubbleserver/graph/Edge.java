package us.bubblenews.bubbleserver.graph;

public interface Edge<T> {
    public T getSource();

    public T getDestination();

    public double getDistance();
}
