package us.bubblenews.bubbleserver.graph.similarity;

import org.jgrapht.graph.DefaultWeightedEdge;

public interface EdgeBuilder<V,E extends DefaultWeightedEdge> {
    public E build(V v1, V v2);
}
