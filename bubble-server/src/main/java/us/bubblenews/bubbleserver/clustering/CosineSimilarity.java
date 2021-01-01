package us.bubblenews.bubbleserver.clustering;

import us.bubblenews.bubbleserver.model.Article;

public class CosineSimilarity implements EdgeWeightCalculator<Article> {
    public CosineSimilarity() {
    }

    @Override
    public double calculateEdgeWeight(Article article1, Article article2) {
        return 0;
    }
}
