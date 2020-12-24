package us.bubblenews.bubbleserver.service;

import us.bubblenews.bubbleserver.model.Vocab;

public interface VocabService {
    public Vocab addToVocabCount(String word, Integer count);
}
