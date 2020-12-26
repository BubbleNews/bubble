package us.bubblenews.bubbleserver.graph;

import java.util.Set;

public interface Graph<T> {
    public Set<Node<T>> getVertices();

    public Set<Edge<T>> getEdges();
}
