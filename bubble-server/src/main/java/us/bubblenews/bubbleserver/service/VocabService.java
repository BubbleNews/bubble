package us.bubblenews.bubbleserver.service;

import us.bubblenews.bubbleserver.model.Vocab;

import java.util.Collection;

public interface VocabService {
    public void addToVocabArticleFrequency(Collection<String> words);
}
