package us.bubblenews.bubbleserver.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class NewsCluster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date date;

    @OneToMany
    private Set<Article> articles;

    public NewsCluster() {
    }

    public NewsCluster(Set<Article> articles) {
        this.articles = articles;
    }
}
