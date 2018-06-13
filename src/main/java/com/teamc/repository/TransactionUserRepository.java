package com.teamc.repository;

import com.teamc.model.TransactionUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionUserRepository extends JpaRepository<TransactionUser, Long> {

    List<TransactionUser> findByUserId(@Param("userId") Long userId);

}
