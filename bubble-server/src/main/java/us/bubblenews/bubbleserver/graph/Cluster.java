package us.bubblenews.bubbleserver.graph;

import us.bubblenews.bubbleserver.graph.Node;

import java.util.Set;

public interface Cluster<T> {
    public Set<Node<T>> getVertices();

    public Node<T> getCoreVertex();

    public double getAverageConnections();

    public double getAverageRadius();

    public double getStandardDeviationRadius();
}
