package us.bubblenews.bubbleserver.api.request;

public class ClusteringParams {
    private double textWeight;

    private double entityWeight;

    private double titleWeight;

    private double edgeWeightThreshold;

    private String clusteringAlgorithmName;

    private String similarityMeasureName;

    private int numberOfClusters;

    public double getTextWeight() {
        return textWeight;
    }

    public void setTextWeight(double textWeight) {
        this.textWeight = textWeight;
    }

    public double getEntityWeight() {
        return entityWeight;
    }

    public void setEntityWeight(double entityWeight) {
        this.entityWeight = entityWeight;
    }

    public double getTitleWeight() {
        return titleWeight;
    }

    public void setTitleWeight(double titleWeight) {
        this.titleWeight = titleWeight;
    }

    public double getEdgeWeightThreshold() {
        return edgeWeightThreshold;
    }

    public void setEdgeWeightThreshold(double edgeWeightThreshold) {
        this.edgeWeightThreshold = edgeWeightThreshold;
    }

    public String getClusteringAlgorithmName() {
        return clusteringAlgorithmName;
    }

    public void setClusteringAlgorithmName(String clusteringAlgorithmName) {
        this.clusteringAlgorithmName = clusteringAlgorithmName;
    }

    public String getSimilarityMeasureName() {
        return similarityMeasureName;
    }

    public void setSimilarityMeasureName(String similarityMeasureName) {
        this.similarityMeasureName = similarityMeasureName;
    }

    public int getNumberOfClusters() {
        return numberOfClusters;
    }

    public void setNumberOfClusters(int numberOfClusters) {
        this.numberOfClusters = numberOfClusters;
    }
}
