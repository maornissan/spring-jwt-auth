package com.maor.learn.spring.jwt.repository;

import com.maor.learn.spring.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select distinct u from User as u left join fetch u.groups ug where u.userName = ?1")
    User findByUserName(String userName);

    @Query("select distinct u from User as u left join fetch u.groups ug where u.id = ?1")
    User findByUserId(Long userId);

    @Query("select distinct u from User as u left join fetch u.groups ug where u.id = ?1")
    User findOne(Long id);

    @Query("select distinct u from User as u left join fetch u.groups ug order by u.id")
    List<User> findAll();
}
