package us.bubblenews.bubbleserver.service.impl;

import org.springframework.stereotype.Service;
import us.bubblenews.bubbleserver.service.StopWordService;

@Service
public class StopWordServiceImpl implements StopWordService {

    @Override
    public boolean isStopWord(String word) {
        return false;
    }
}
