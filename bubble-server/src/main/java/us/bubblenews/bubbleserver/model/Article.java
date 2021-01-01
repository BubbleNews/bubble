package us.bubblenews.bubbleserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
public class Article implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String url;

    private Date timePublished;

    @ManyToOne
    private NewsSource source;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.LAZY)
    @MapKeyColumn(name = "word")
    @Column(name = "count")
    private Map<String, Integer> wordFrequencyMap;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getTimePublished() {
        return timePublished;
    }

    public void setTimePublished(Date timePublished) {
        this.timePublished = timePublished;
    }

    public NewsSource getSource() {
        return source;
    }

    public void setSource(NewsSource source) {
        this.source = source;
    }

    public Map<String, Integer> getWordFrequencyMap() {
        return wordFrequencyMap;
    }

    public void setWordFrequencyMap(Map<String, Integer> wordCountMap) {
        this.wordFrequencyMap = wordCountMap;
    }
}