package us.bubblenews.bubbleserver.graph.similarity;

import us.bubblenews.bubbleserver.model.Article;
import us.bubblenews.bubbleserver.model.Vocab;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ArticleSimilarityCalculator implements EdgeWeightCalculator<Article> {
    private Map<String, Integer> wordToArticleFrequencyMap;

    private int numArticles;

    private SimilarityMeasure<String> similarityMeasure;

    public ArticleSimilarityCalculator(Collection<Vocab> vocabulary, int numArticles, SimilarityMeasure<String> similarityMeasure) {
        this.wordToArticleFrequencyMap = makeWordToArticleFrequencyMap(vocabulary);
        this.numArticles = numArticles;
        this.similarityMeasure = similarityMeasure;
    }

    @Override
    public double calculateEdgeWeight(Article node1, Article node2) {
        Map<String, Double> node1WordVector = getNormalizedAndWeightedWordFrequencyVector(node1);
        Map<String, Double> node2WordVector = getNormalizedAndWeightedWordFrequencyVector(node2);
        return similarityMeasure.calculate(node1WordVector, node2WordVector);
    }

    private Map<String, Double> getNormalizedAndWeightedWordFrequencyVector(Article article) {
        Map<String, Double> wordFrequencyVector = article.getNormalizedWordFrequencyVector();
        for (Map.Entry<String, Double> wordEntry : wordFrequencyVector.entrySet()) {
            if (wordToArticleFrequencyMap.containsKey(wordEntry.getKey())) {
                double tfIdf = wordEntry.getValue() * getInverseDocumentFrequency(wordEntry.getKey());
                wordEntry.setValue(tfIdf);
            } else {
                System.err.println("Word '" + wordEntry.getKey() + "' from article " + article.getId() + " was not present in vocabulary.");
            }
        }
        return wordFrequencyVector;
    }

    private Map<String, Integer> makeWordToArticleFrequencyMap(Collection<Vocab> vocabulary) {
        Map<String, Integer> map = new HashMap<>();
        vocabulary.stream()
                .forEach(vocab -> {
                    map.put(vocab.getWord(), vocab.getArticleFrequency());
                });
        return map;
    }

    private double getInverseDocumentFrequency(String word) {
        return Math.log(numArticles / wordToArticleFrequencyMap.get(word));
    }
}
