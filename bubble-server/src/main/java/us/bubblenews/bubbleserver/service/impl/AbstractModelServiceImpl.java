package us.bubblenews.bubbleserver.service.impl;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModelServiceImpl<T> {
    protected List<T> iterableToList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        for (T item : iterable) {
            list.add(item);
        }
        return list;
    }
}
