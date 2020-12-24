package us.bubblenews.bubbleserver.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class NewsCluster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany
    private List<Article> articles;

    private double averageConnections;

    private double averageRadius;
}
