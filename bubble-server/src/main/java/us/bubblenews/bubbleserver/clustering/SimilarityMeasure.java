package us.bubblenews.bubbleserver.clustering;

import java.util.Map;

public abstract class SimilarityMeasure<K> {
    public abstract double calculate(Map<K, Double> vector1, Map<K, Double> vector2);

    protected double getDotProductOfVectors(Map<K, Double> vector1, Map<K, Double> vector2) {
        double sum = 0;
        for (K key : vector1.keySet()) {
            if (vector2.containsKey(key)) {
                sum += vector1.get(key) * vector2.get(key);
            }
        }
        return sum;
    }

    protected double getMagnitudeOfVector(Map<K, Double> vector) {
        double sumOfSquares = 0;
        for (Double component : vector.values()) {
            sumOfSquares += Math.pow(component, 2);
        }
        return Math.sqrt(sumOfSquares);
    }
}
