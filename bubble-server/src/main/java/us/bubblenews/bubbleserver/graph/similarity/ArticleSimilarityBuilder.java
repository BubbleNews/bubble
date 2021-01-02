package us.bubblenews.bubbleserver.graph.similarity;

import us.bubblenews.bubbleserver.model.Article;
import us.bubblenews.bubbleserver.model.Vocab;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ArticleSimilarityBuilder implements EdgeBuilder<Article, ArticleSimilarity> {
    private Map<String, Integer> wordToArticleFrequencyMap;

    private int numArticles;

    private SimilarityMeasure<String> similarityMeasure;

    private SimilarityWeights weights;

    public ArticleSimilarityBuilder(Collection<Vocab> vocabulary, int numArticles, SimilarityMeasure<String> similarityMeasure,
                                    SimilarityWeights weights) {
        this.wordToArticleFrequencyMap = makeWordToArticleFrequencyMap(vocabulary);
        this.numArticles = numArticles;
        this.similarityMeasure = similarityMeasure;
        this.weights = weights;
    }

    @Override
    public ArticleSimilarity build(Article v1, Article v2) {
        Map<String, Double> node1WordVector = getNormalizedAndWeightedWordFrequencyVector(v1);
        Map<String, Double> node2WordVector = getNormalizedAndWeightedWordFrequencyVector(v2);
        double textSimilarity = similarityMeasure.calculate(node1WordVector, node2WordVector);
        // TODO: implement entity and title similarity
        double entitySimilarity = 0;
        double titleSimilarity = 0;
        ArticleSimilarity edge = new ArticleSimilarity();
        edge.setWeightedTextSimilarity(weights.getTextWeight() * textSimilarity);
        edge.setWeightedEntitySimilarity(weights.getEntityWeight() * entitySimilarity);
        edge.setWeightedTitleSimilarity(weights.getTitleWeight() + titleSimilarity);
        return edge;
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
