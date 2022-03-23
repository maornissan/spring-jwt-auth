/*
 * Copyright (c) 2020. Maor Nissan.
 * This is a private code, not opened source.
 */

package com.maor.learn.spring.jwt.repository;

import com.maor.learn.spring.jwt.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface PermissionRepository extends JpaRepository<Permission, Long> {


    @Query("select p from Permission as p where p.id = ?1")
    Permission findOne(Long id);

    @Query("select p from Permission as p order by p.id")
    List<Permission> findAll();

    @Query("select distinct p from Permission as p join fetch p.groups groups where groups.id in (?1)")
    List<Permission> findByGroups(Set<Long> groupIds);
}
