package us.bubblenews.bubbleserver.repository;

import org.springframework.data.repository.CrudRepository;
import us.bubblenews.bubbleserver.model.Vocab;

public interface VocabRepository extends CrudRepository<Vocab, String> {
}
