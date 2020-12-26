package us.bubblenews.bubbleserver.service.impl;

import org.springframework.stereotype.Service;
import us.bubblenews.bubbleserver.graph.Cluster;
import us.bubblenews.bubbleserver.graph.Graph;
import us.bubblenews.bubbleserver.model.Article;
import us.bubblenews.bubbleserver.service.ClusteringService;

import java.util.HashSet;
import java.util.Set;

@Service("Alpha")
public class ClusteringServiceAlphaImpl implements ClusteringService {

    @Override
    public Set<Cluster<Article>> createClusters(Graph<Article> articleGraph) {
        // TODO: implement clustering1
        return new HashSet<>();
    }
}
