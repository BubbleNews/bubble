package us.bubblenews.bubbleserver.graph.similarity;

public class SimilarityWeights {
    private double textWeight;

    private double titleWeight;

    private double entityWeight;

    public double getTextWeight() {
        return textWeight;
    }

    public void setTextWeight(double textWeight) {
        this.textWeight = textWeight;
    }

    public double getTitleWeight() {
        return titleWeight;
    }

    public void setTitleWeight(double titleWeight) {
        this.titleWeight = titleWeight;
    }

    public double getEntityWeight() {
        return entityWeight;
    }

    public void setEntityWeight(double entityWeight) {
        this.entityWeight = entityWeight;
    }
}
