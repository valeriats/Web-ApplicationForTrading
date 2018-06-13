package com.teamc.repository;

import com.teamc.model.News;
import com.teamc.model.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    News findByTitle(@Param("title") String title);

    News findById(@Param("newsId") long newsId);
}
