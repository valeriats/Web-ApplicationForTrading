package com.teamc.repository;


import com.teamc.model.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {

    Share findByName(@Param("name") String name);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "SELECT * FROM share ORDER BY random() LIMIT 3", nativeQuery = true)
    List<Share> threeRandomShare();

}
