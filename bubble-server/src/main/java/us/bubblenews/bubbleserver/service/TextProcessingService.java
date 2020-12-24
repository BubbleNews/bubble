package us.bubblenews.bubbleserver.service;

import java.util.Map;

public interface TextProcessingService {
    public Map<String, Integer> getWordFrequencyMap(String text, String delimiter, boolean doLemmatize, boolean doRemoveStopWords);
}
