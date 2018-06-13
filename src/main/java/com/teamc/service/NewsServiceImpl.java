package com.teamc.service;

import com.teamc.model.News;
import com.teamc.repository.NewsRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@Slf4j
public class NewsServiceImpl implements NewsService {

    private NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public void save(News news) {
        newsRepository.save(news);
    }

    @Override
    public void update(String name, News news) {
        News news1 = newsRepository.findByTitle(name);
        if (news1 == null) {
            newsRepository.save(news);
        }
    }

    public News findById(long id) {
       return newsRepository.findById(id);
    }
}
