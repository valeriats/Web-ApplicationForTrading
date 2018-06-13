package com.teamc.repository;

import com.teamc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByFirstName(@Param("firstName") String firstName);

    User findByUsername(@Param("username") String username);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "update users set priz_status = false", nativeQuery = true)
    Integer setStatusFalse();

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "update users set priz_status = true where extract (month from birthday) = extract (month from current_date ) and extract (day from birthday) = extract (day from current_date )", nativeQuery = true)
    void setStatusTrue();

}
