package com.teamc.service;

import com.teamc.model.News;
import com.teamc.model.Share;

import java.util.List;

public interface NewsService {

    List<News> findAll();

    void save(News user);

    void update(String name, News news);

    News findById(long id);

}
