package us.bubblenews.bubbleserver.repository;

import org.springframework.data.repository.CrudRepository;
import us.bubblenews.bubbleserver.model.Vocab;

import java.util.Collection;
import java.util.List;

public interface VocabRepository extends CrudRepository<Vocab, String> {
    List<Vocab> findAllByWordIn(Collection<String> words);
}
