package us.bubblenews.bubbleserver.clustering;

import us.bubblenews.bubbleserver.graph.Cluster;
import us.bubblenews.bubbleserver.graph.Edge;
import us.bubblenews.bubbleserver.graph.Graph;
import us.bubblenews.bubbleserver.graph.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ClustererAlpha<T> implements Clusterer<T> {
    private final double MIN_MAX_MULT = 1.3;
    private final double MAX_MAX_MULT = 2.6;
    private final double ITER_MAX_MULT = 0.1;
    private final double TIGHT_CLUSTER = 0.5;

    private Graph<T> graph;
    private Map<Node<T>, Cluster<T>> nodeToTemporaryClusterMap = new HashMap<>();

    @Override
    public Set<Cluster<T>> createClusters(Graph<T> graph, double threshold) {
        this.graph = graph;
        Set<Edge<T>> edges = getEdgesWithinThreshold(graph.getEdges(), threshold);
        for (Edge<T> edge : edges) {
            Node<T> source = edge.getSource();
            Node<T> destination = edge.getDestination();
            // if both nodes of current edge are clustered, decides whether to combine clusters
            if (nodeToTemporaryClusterMap.containsKey(source) && nodeToTemporaryClusterMap.containsKey(destination)) {
                Cluster<T> sourceCluster = nodeToTemporaryClusterMap.get(source);
                Cluster<T> destinationCluster = nodeToTemporaryClusterMap.get(destination);
                if (shouldCombineClusters(sourceCluster, destinationCluster)) {
                    combineClusters(sourceCluster, destinationCluster);
                }
            }

        }
    }

    private Set<Edge<T>> getEdgesWithinThreshold(Set<Edge<T>> edges, double threshold) {
        return edges
                .stream()
                .filter(edge -> edge.getDistance() <= threshold)
                .collect(Collectors.toSet());
    }

    private boolean shouldCombineClusters(Cluster<T> cluster1, Cluster<T> cluster2) {
        //TODO: implement should combine clusters
        return true;
    }

    private void combineClusters(Cluster<T> cluster1, Cluster<T> cluster2) {
        for (Node<T> node : cluster2.getVertices()) {
            nodeToTemporaryClusterMap.replace(node, cluster1);
        }
    }

    private boolean shouldAddNodeToCluster(Cluster<T> cluster, Node<T> node) {

    }

    private void addNodeToCluster(Cluster<T> cluster, Node<T> node) {
        nodeToTemporaryClusterMap.put(node, cluster);
    }
}
