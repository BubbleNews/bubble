package us.bubblenews.bubbleserver.service;

import us.bubblenews.bubbleserver.model.NewsSource;

public interface NewsSourceService {
    public NewsSource getByNameOrCreate(String name);
}
