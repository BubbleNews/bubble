package us.bubblenews.bubbleserver.graph;

public interface Edge<T> {
    public Node<T> getSource();

    public Node<T> getDestination();

    public double getDistance();
}
