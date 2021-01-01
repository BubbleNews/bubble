package us.bubblenews.bubbleserver.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class NewsCluster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany
    private Set<Article> articles;

    public NewsCluster() {
    }

    public NewsCluster(Set<Article> articles) {
        this.articles = articles;
    }
}
