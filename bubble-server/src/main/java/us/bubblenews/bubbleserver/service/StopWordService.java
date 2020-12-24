package us.bubblenews.bubbleserver.service;

import org.springframework.stereotype.Service;

public interface StopWordService {

    public boolean isStopWord(String word);
}
