package us.bubblenews.bubbleserver.service;

import us.bubblenews.bubbleserver.model.Vocab;

import java.util.Map;

public interface VocabService {
    public void addToVocabArticleFrequency(Map<String, Integer> wordFrequencies);
}
