package us.bubblenews.bubbleserver.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vocab {

    @Id
    private String word;

    private Integer count;

    public Vocab() {
    }

    public Vocab(String word, Integer count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
