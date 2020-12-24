package us.bubblenews.bubbleserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.bubblenews.bubbleserver.model.NewsSource;
import us.bubblenews.bubbleserver.repository.NewsSourceRepository;
import us.bubblenews.bubbleserver.service.NewsSourceService;

import java.util.List;

@Service
public class NewsSourceServiceImpl extends AbstractModelServiceImpl<NewsSource> implements NewsSourceService {

    @Autowired
    NewsSourceRepository repository;

    @Override
    public NewsSource getByNameOrCreate(String name) {
        List<NewsSource> sources = repository.findNewsSourcesByName(name);
        if (sources.isEmpty()) {
            NewsSource source = new NewsSource();
            source.setName(name);
            return repository.save(source);
        } else {
            return sources.get(0);
        }
    }
}
