package us.bubblenews.bubbleserver.graph.clustering;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.*;
import java.util.stream.Collectors;

public class GravesClustering<E extends DefaultWeightedEdge> implements ClusteringAlgorithm<E> {
    private SimpleWeightedGraph<Integer, E> graph;
    private EdgeComparator<E> edgeComparator;
    private List<E> edges;
    private Set<Cluster> clusters;
    private Map<Integer, Cluster> vertexToCluster;
    private double overallEdgeWeightThreshold;
    private double clusterEdgeWeightThreshold;

    private final double MIN_MAX_MULT = 1.3;
    private final double MAX_MAX_MULT = 2.6;
    private final double ITER_MAX_MULT = 0.1;
    private final double TIGHT_CLUSTER = 0.5;


    @Override
    public List<Cluster> getClusters(SimpleWeightedGraph<Integer, E> graph) {
        clusters = new HashSet<>();
        vertexToCluster = new HashMap<>();
        this.graph = graph;
        edges = new ArrayList<>(graph.edgeSet());
        edgeComparator = new EdgeComparator<>(graph);

        setThresholds();
        trimEdges();
        createClusters();
        return new ArrayList<>(clusters);
    }

    private void setThresholds() {
        // TODO: get threshold calculations right
        overallEdgeWeightThreshold = 0.5;
        clusterEdgeWeightThreshold = 0.1;
    }

    private void trimEdges() {
        edges = edges
                .stream()
                .filter(edge -> graph.getEdgeWeight(edge) <= overallEdgeWeightThreshold)
                .collect(Collectors.toList());
        edges.sort(edgeComparator);
    }

    private void createClusters() {
        for (E currentEdge : edges) {
            int source = graph.getEdgeSource(currentEdge);
            int target = graph.getEdgeTarget(currentEdge);
            if (vertexToCluster.containsKey(source) && vertexToCluster.containsKey(target)) {
                Cluster<E> sourceCluster = vertexToCluster.get(source);
                Cluster<E> targetCluster = vertexToCluster.get(target);
                if (shouldCombineClusters(sourceCluster, targetCluster)) {
                    combineClusters(sourceCluster, targetCluster);
                }
            } else if (vertexToCluster.containsKey(source)) {
                Cluster<E> sourceCluster = vertexToCluster.get(source);
                if (shouldAddVertexToCluster(target, sourceCluster)) {
                    addVertexToCluster(target, sourceCluster);
                }
            } else if (vertexToCluster.containsKey(target)) {
                Cluster<E> targetCluster = vertexToCluster.get(target);
                if (shouldAddVertexToCluster(source, targetCluster)) {
                    addVertexToCluster(source, targetCluster);
                }
            } else {
                if (shouldCreateClusterFromVertices(source, target)) {
                    createClusterFromVertices(source, target);
                }
            }
        }
    }

    private boolean shouldCombineClusters(Cluster<E> cluster1, Cluster<E> cluster2) {
        double averageEdgeWeightIfCombined = cluster1.getAverageEdgeWeightWithOtherCluster(cluster2);
        // TODO : Ask John about the maxMult part here (why Math.min?)

        return averageEdgeWeightIfCombined <= clusterEdgeWeightThreshold;
    }

    private boolean shouldAddVertexToCluster(Integer vertex, Cluster<E> cluster) {
        double averageEdgeWeightIfAdded = cluster.getAverageEdgeWeightWithOtherVertex(vertex);
//       double maxMult = Math.max(MAX_MAX_MULT - (ITER_MAX_MULT * cluster.getCurrentSize()), MIN_MAX_MULT);
//       double difference = averageEdgeWeightIfAdded / (TIGHT_CLUSTER * clusterEdgeWeightThreshold);
//
//       return averageEdgeWeightIfAdded < clusterEdgeWeightThreshold
//               && averageEdgeWeightIfAdded < maxMult * cluster.getAverageEdgeWeight()
//               && difference < 1;
        return averageEdgeWeightIfAdded <= clusterEdgeWeightThreshold;
    }

    private boolean shouldCreateClusterFromVertices(Integer vertex1, Integer vertex2) {
        // TODO: explore threshold / 3.0 like in original code
        return graph.getEdgeWeight(graph.getEdge(vertex1, vertex2)) <= clusterEdgeWeightThreshold;
    }

    private void combineClusters(Cluster<E> cluster1, Cluster<E> cluster2) {
        cluster1.absorbCluster(cluster2);
        cluster2.getVertices().forEach(v -> vertexToCluster.put(v, cluster1));
        clusters.remove(cluster2);
    }

    private void addVertexToCluster(Integer vertex, Cluster<E> cluster) {
        cluster.addVertex(vertex);
        vertexToCluster.put(vertex, cluster);
    }

    private void createClusterFromVertices(Integer vertex1, Integer vertex2) {
        Cluster<E> newCluster = new Cluster<>(graph);
        newCluster.addVertex(vertex1);
        newCluster.addVertex(vertex2);
    }
}
