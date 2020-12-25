package us.bubblenews.bubbleserver.service;

import java.util.Map;
import java.util.Set;

public interface TextProcessingService {
    public Map<String, Integer> getTermFrequencyMap(String text, boolean doRemoveStopWords, boolean doStem);
}
