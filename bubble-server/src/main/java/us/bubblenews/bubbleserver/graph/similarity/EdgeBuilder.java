package us.bubblenews.bubbleserver.graph.similarity;

public interface EdgeBuilder<V,E extends SimpleWeighted> {
    public E build(V v1, V v2);
}
