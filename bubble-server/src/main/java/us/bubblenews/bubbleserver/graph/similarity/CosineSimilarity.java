package us.bubblenews.bubbleserver.graph.similarity;

import java.util.Map;

public class CosineSimilarity<K> extends SimilarityMeasure<K> {

    @Override
    public double calculate(Map<K, Double> vector1, Map<K, Double> vector2) {
        return getDotProductOfVectors(vector1, vector2) / (getMagnitudeOfVector(vector1) * getMagnitudeOfVector(vector2));
    }
}
