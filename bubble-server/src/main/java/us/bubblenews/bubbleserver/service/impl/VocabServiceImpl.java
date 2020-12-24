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
    public void addToVocabArticleFrequency(Collection<String> words) {
        Map<String, Vocab> wordsToVocabs = getWordToVocabMap(words);
        for (String word : words) {
            Vocab toSave = wordsToVocabs.getOrDefault(word, new Vocab(word, 0));
            toSave.setArticleFrequency(toSave.getArticleFrequency() + 1);
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
