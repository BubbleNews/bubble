package us.bubblenews.bubbleserver.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import us.bubblenews.bubbleserver.api.request.ClusteringParams;
import us.bubblenews.bubbleserver.graph.clustering.ClusteringAlgorithm;
import us.bubblenews.bubbleserver.graph.clustering.KSpanningTreeClusteringWrapper;
import us.bubblenews.bubbleserver.graph.similarity.*;
import us.bubblenews.bubbleserver.model.Article;
import us.bubblenews.bubbleserver.model.NewsCluster;
import us.bubblenews.bubbleserver.model.Vocab;
import us.bubblenews.bubbleserver.service.ArticleService;
import us.bubblenews.bubbleserver.service.NewsClusterService;
import us.bubblenews.bubbleserver.service.VocabService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="/clusters")
public class NewsClusterController {

    @Autowired
    private NewsClusterService newsClusterService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private VocabService vocabService;

    @PostMapping
    public List<NewsCluster> makeClusters(@RequestParam("date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date,
                                          @RequestBody ClusteringParams clusteringParams) {
        List<Vocab> vocabulary = vocabService.findAll();
        List<Article> articles = articleService.findArticlesByDate(date);
        ClusteringAlgorithm<ArticleSimilarity> algorithm = getClusteringAlgorithmFromClusteringParams(clusteringParams);
        SimilarityMeasure<String> similarityMeasure = getGetSimilarityMeasureFromClusteringParams(clusteringParams);
        SimilarityWeights weights = getSimilarityWeightsFromClusteringParams(clusteringParams);
        ArticleSimilarityBuilder edgeBuilder = new ArticleSimilarityBuilder(vocabulary, articles.size(), similarityMeasure, weights);

        List<NewsCluster> clusters = newsClusterService.makeClustersFromArticles(articles, edgeBuilder, algorithm,
                clusteringParams.getEdgeWeightThreshold());
        return clusters;
    }

    @GetMapping("/all")
    public List<NewsCluster> getClusters(@RequestParam("date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date) {
        return newsClusterService.getClustersForDate(date);
    }

    private ClusteringAlgorithm<ArticleSimilarity> getClusteringAlgorithmFromClusteringParams(ClusteringParams params) {
        switch(params.getClusteringAlgorithmName()) {
            case "KSpanningTree":
            default:
                return new KSpanningTreeClusteringWrapper<>(params.getNumberOfClusters());
        }
    }

    private SimilarityMeasure<String> getGetSimilarityMeasureFromClusteringParams(ClusteringParams params) {
        switch(params.getSimilarityMeasureName()) {
            case "CosineSimilarity":
            default:
                return new CosineSimilarity<>();
        }
    }

    private SimilarityWeights getSimilarityWeightsFromClusteringParams(ClusteringParams params) {
        SimilarityWeights similarityWeights = new SimilarityWeights();
        similarityWeights.setTextWeight(params.getTextWeight());
        similarityWeights.setEntityWeight(params.getEntityWeight());
        similarityWeights.setTitleWeight(params.getTitleWeight());
        return similarityWeights;
    }
}
