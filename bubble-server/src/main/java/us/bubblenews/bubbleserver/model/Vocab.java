package us.bubblenews.bubbleserver.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vocab {

    @Id
    private String word;

    private Integer articleFrequency;

    public Vocab() {
    }
    public Vocab(String word, Integer articleFrequency) {
        this.word = word;
        this.articleFrequency = articleFrequency;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getArticleFrequency() {
        return articleFrequency;
    }

    public void setArticleFrequency(Integer articleFrequency) {
        this.articleFrequency = articleFrequency;
    }
}
