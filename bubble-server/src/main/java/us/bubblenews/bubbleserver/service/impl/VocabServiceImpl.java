package us.bubblenews.bubbleserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.bubblenews.bubbleserver.model.Vocab;
import us.bubblenews.bubbleserver.repository.VocabRepository;
import us.bubblenews.bubbleserver.service.VocabService;

import java.util.*;

@Service
public class VocabServiceImpl extends AbstractModelServiceImpl<Vocab> implements VocabService {

    @Autowired
    private VocabRepository repository;

    // We need to consider concurrency issues
    @Override
    public void addToVocabArticleFrequency(Map<String, Integer> wordFrequencies) {
        Map<String, Vocab> wordsToVocabs = getWordToVocabMap(wordFrequencies.keySet());
        for (String word : wordFrequencies.keySet()) {
            Vocab toSave = wordsToVocabs.getOrDefault(word, new Vocab(word, 0));
            toSave.setArticleFrequency(toSave.getArticleFrequency() + wordFrequencies.get(word));
            repository.save(toSave);
        }
    }

    private Map<String, Vocab> getWordToVocabMap(Collection<String> words) {
        Map<String, Vocab> map = new HashMap<>();
        repository.findAllByWordIn(words)
                .stream()
                .forEach(v -> map.put(v.getWord(), v));
        return map;
    }
}
