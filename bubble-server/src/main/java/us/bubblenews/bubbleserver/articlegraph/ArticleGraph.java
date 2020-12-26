package us.bubblenews.bubbleserver.articlegraph;

import us.bubblenews.bubbleserver.graph.Edge;
import us.bubblenews.bubbleserver.graph.Graph;
import us.bubblenews.bubbleserver.graph.Node;
import us.bubblenews.bubbleserver.model.Article;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ArticleGraph implements Graph<Article> {
    private Set<Node<Article>> vertices;

    private Set<Edge<Article>> edges;

    public ArticleGraph(Collection<Article> articles, ) {
        vertices = new HashSet<>();
        for (Article article : articles) {
            vertices.add(new ArticleNode(article));
        }
        edges = new HashSet<>();
        for (Node<Article> vertex1 : vertices) {
            for (Node<Article> vertex2 : vertices) {
                if (vertex1.hashCode() < vertex2.hashCode()) {
                    edges.add(new ArticleCosineSimilarity());
                }
            }
        }
    }

    @Override
    public Set<Node<Article>> getVertices() {
        return null;
    }

    @Override
    public Set<Edge<Article>> getEdges() {
        return null;
    }
}
