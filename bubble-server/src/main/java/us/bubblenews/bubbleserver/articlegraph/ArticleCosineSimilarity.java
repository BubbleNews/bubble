package us.bubblenews.bubbleserver.articlegraph;

import us.bubblenews.bubbleserver.clustering.ClusteringParams;
import us.bubblenews.bubbleserver.graph.Edge;
import us.bubblenews.bubbleserver.graph.Node;
import us.bubblenews.bubbleserver.model.Article;

public class ArticleCosineSimilarity implements Edge<Article> {
    private Node<Article> source;

    private Node<Article> destination;

    private double distance;

    public ArticleCosineSimilarity(Node<Article> source, Node<Article> destination, ClusteringParams params) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public Node<Article> getSource() {
        return source;
    }

    public void setSource(Node<Article> source) {
        this.source = source;
    }

    @Override
    public Node<Article> getDestination() {
        return destination;
    }

    public void setDestination(Node<Article> destination) {
        this.destination = destination;
    }

    @Override
    public double getDistance() {
        return distance;
    }

    private void calculateDistances() {

    }
}
