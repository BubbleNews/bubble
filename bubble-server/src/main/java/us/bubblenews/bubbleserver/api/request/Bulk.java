package us.bubblenews.bubbleserver.api.request;

import java.util.List;

public class Bulk<T> {

    private List<T> items;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
