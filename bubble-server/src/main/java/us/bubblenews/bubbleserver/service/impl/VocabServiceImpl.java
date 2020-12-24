package us.bubblenews.bubbleserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.bubblenews.bubbleserver.model.Vocab;
import us.bubblenews.bubbleserver.repository.VocabRepository;
import us.bubblenews.bubbleserver.service.VocabService;

import java.util.Optional;

@Service
public class VocabServiceImpl extends AbstractModelServiceImpl<Vocab> implements VocabService {

    @Autowired
    private VocabRepository repository;

    // We need to consider concurrency issues
    @Override
    public Vocab addToVocabCount(String word, Integer count) {
        Vocab newCount = new Vocab(word, count);
        Optional<Vocab> existingVocab = repository.findById(word);
        if (existingVocab.isPresent()) {
            newCount.setCount(newCount.getCount() + existingVocab.get().getCount());
        }
        return repository.save(newCount);
    }
}
