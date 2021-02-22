package us.bubblenews.bubbleserver.graph.clustering;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.HashSet;
import java.util.Set;

public class Cluster<E extends DefaultWeightedEdge> {
    private SimpleWeightedGraph<Integer, E> parentGraph;
    private Set<Integer> vertices;
    private double averageEdgeWeight;

    public Cluster(SimpleWeightedGraph<Integer, E> graph) {
        parentGraph = graph;
        vertices = new HashSet<>();
    }

    public Cluster(SimpleWeightedGraph<Integer, E> graph, Set<Integer> vertices) {
        parentGraph = graph;
        this.vertices = vertices;
    }

    /**
     * Calculates the new average edge weight within the cluster if another vertex were to be added the cluster.
     *
     * @param otherVertex the other vertex
     * @return the new average edge weight if the other vertex were added to the cluster
     */
    public double getAverageEdgeWeightWithOtherVertex(Integer otherVertex) {
        if (vertices.contains(otherVertex)) {
            System.err.println("getAverageEdgeWeightWithOtherVertex: vertex already belongs to cluster");
            return averageEdgeWeight;
        }
        int currentSize = getCurrentSize();
        int currentEdgeCount = getCurrentEdgeCount();

        double newEdgeWeightSum = getCurrentEdgeWeightSum();
        for (Integer vertexInCluster : vertices) {
            E edge = parentGraph.getEdge(otherVertex, vertexInCluster);
            newEdgeWeightSum += parentGraph.getEdgeWeight(edge);
        }
        int newEdgeCount = currentEdgeCount + currentSize;
        return newEdgeWeightSum / newEdgeCount;
    }

    /**
     * Calculates the new average edge weight of the cluster resulting from combining the cluster with
     * another cluster.
     *
     * @param otherCluster the other cluster
     * @return the new average edge weight of the potential new cluster
     */
    public double getAverageEdgeWeightWithOtherCluster(Cluster<E> otherCluster) {
        double newEdgeWeightSum = getCurrentEdgeWeightSum() + otherCluster.getCurrentEdgeWeightSum();
        for (Integer vertexInCluster : vertices) {
            Set<Integer> otherClusterVertices = otherCluster.getVertices();
            if (otherClusterVertices.contains(vertexInCluster)) {
                System.err.println("getAverageEdgeWeightWithOtherCluster: overlap between clusters");
            }
            for (Integer vertexInOtherCluster : otherClusterVertices) {
                E edge = parentGraph.getEdge(vertexInCluster, vertexInOtherCluster);
                newEdgeWeightSum += parentGraph.getEdgeWeight(edge);
            }
        }
        int newEdgeCount = getCurrentEdgeCount() + otherCluster.getCurrentEdgeCount()
                + getCurrentSize() * otherCluster.getCurrentSize();
        return newEdgeWeightSum / newEdgeCount;
    }

    public void addVertex(Integer vertexToAdd) {
        averageEdgeWeight = getAverageEdgeWeightWithOtherVertex(vertexToAdd);
        vertices.add(vertexToAdd);
    }

    public void absorbCluster(Cluster<E> clusterToAbsorb) {
        averageEdgeWeight = getAverageEdgeWeightWithOtherCluster(clusterToAbsorb);
        vertices.addAll(clusterToAbsorb.getVertices());
    }

    public Set<Integer> getVertices() {
        return vertices;
    }

    public double getAverageEdgeWeight() {
        return averageEdgeWeight;
    }

    public void setAverageEdgeWeight(double averageEdgeWeight) {
        this.averageEdgeWeight = averageEdgeWeight;
    }

    public int getCurrentSize() {
        return vertices.size();
    }

    private int getCurrentEdgeCount() {
        return (getCurrentSize() * (getCurrentSize() - 1)) / 2;
    }

    private double getCurrentEdgeWeightSum() {
        return getCurrentEdgeCount() * averageEdgeWeight;
    }
}
