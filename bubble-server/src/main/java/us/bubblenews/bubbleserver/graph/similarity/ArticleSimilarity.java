package us.bubblenews.bubbleserver.graph.similarity;

import org.jgrapht.graph.DefaultWeightedEdge;

public class ArticleSimilarity extends DefaultWeightedEdge {
    private double weightedTextSimilarity;

    private double weightedEntitySimilarity;

    private double weightedTitleSimilarity;

    @Override
    public double getWeight() {
        return weightedTextSimilarity + weightedEntitySimilarity + weightedTitleSimilarity;
    }

    public double getWeightedTextSimilarity() {
        return weightedTextSimilarity;
    }

    public void setWeightedTextSimilarity(double weightedTextSimilarity) {
        this.weightedTextSimilarity = weightedTextSimilarity;
    }

    public double getWeightedEntitySimilarity() {
        return weightedEntitySimilarity;
    }

    public void setWeightedEntitySimilarity(double weightedEntitySimilarity) {
        this.weightedEntitySimilarity = weightedEntitySimilarity;
    }

    public double getWeightedTitleSimilarity() {
        return weightedTitleSimilarity;
    }

    public void setWeightedTitleSimilarity(double weightedTitleSimilarity) {
        this.weightedTitleSimilarity = weightedTitleSimilarity;
    }
}
