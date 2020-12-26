package us.bubblenews.bubbleserver.clustering;

import us.bubblenews.bubbleserver.graph.Cluster;
import us.bubblenews.bubbleserver.graph.Graph;

import java.util.Set;

public interface Clusterer<T> {
    public Set<Cluster<T>> createClusters(Graph<T> graph, double threshold);
}
