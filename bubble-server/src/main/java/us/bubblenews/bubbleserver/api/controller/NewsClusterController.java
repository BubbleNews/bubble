package us.bubblenews.bubbleserver.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import us.bubblenews.bubbleserver.model.NewsCluster;
import us.bubblenews.bubbleserver.service.NewsClusterService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="/clusters")
public class NewsClusterController {

    @Autowired
    private NewsClusterService newsClusterService;

    @PostMapping
    public List<NewsCluster> makeClusters(@RequestParam("date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date) {


        return newsClusterService.makeClustersFromArticles(articlesForDate, );
    }

    @GetMapping("/all")
    public List<NewsCluster> getClusters(@RequestParam("date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date) {
        return newsClusterService.getClustersForDate(date);
    }
}
