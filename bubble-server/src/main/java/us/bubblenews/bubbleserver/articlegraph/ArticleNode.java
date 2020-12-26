package us.bubblenews.bubbleserver.articlegraph;

import us.bubblenews.bubbleserver.graph.Node;
import us.bubblenews.bubbleserver.model.Article;

public class ArticleNode implements Node<Article> {
    private Article value;

    public ArticleNode(Article value) {
        this.value = value;
    }

    @Override
    public Article getValue() {
        return null;
    }
}
