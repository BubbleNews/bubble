package us.bubblenews.bubbleserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.bubblenews.bubbleserver.service.LemmatizationService;
import us.bubblenews.bubbleserver.service.StopWordService;
import us.bubblenews.bubbleserver.service.TextProcessingService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TextProcessingServiceImpl implements TextProcessingService {

    @Autowired
    LemmatizationService lemmatizationService;

    @Autowired
    StopWordService stopWordService;

    @Override
    public Map<String, Integer> getWordFrequencyMap(String text, String delimiter, boolean doLemmatize, boolean doRemoveStopWords) {
        Map<String, Integer> termFrequency = new HashMap<>();
        List<String> words = Arrays.asList(text.split(delimiter));
        for (String word : words) {
            if (doLemmatize) {
                word = lemmatizationService.lemmatizeWord(word);
            }
            if (doRemoveStopWords && stopWordService.isStopWord(word)) {
                continue;
            }
            termFrequency.put(word, termFrequency.getOrDefault(word, 0) + 1);
        }
        return termFrequency;
    }
}
