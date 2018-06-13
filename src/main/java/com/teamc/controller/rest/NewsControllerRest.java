package com.teamc.controller.rest;

import com.teamc.model.News;
import com.teamc.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsControllerRest {

    NewsService newsService;

    @Autowired
    public NewsControllerRest(NewsService newsService) {
        this.newsService = newsService;
    }

    @RequestMapping(value = "/get/news/{newsId}", produces = "application/json")
    public News getNew(@PathVariable("newsId") long id) {
        return newsService.findById(id);
    }
}
