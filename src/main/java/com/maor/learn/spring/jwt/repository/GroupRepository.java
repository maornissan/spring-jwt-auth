
/*
 * Copyright (c) 2020. Maor Nissan.
 * This is a private code, not opened source.
 */

package com.maor.learn.spring.jwt.repository;

import com.maor.learn.spring.jwt.model.Group;
import com.maor.learn.spring.jwt.model.GroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findById(Integer id);

    @Query("select distinct g from Group as g left join fetch g.permissions perms order by g.id")
    List<Group> findAll();

    @Query("select distinct g from Group as g left join fetch g.permissions perms where perms.id in (?1) ")
    List<Group> findByPermissions(Set<Integer> permissionIds);

    @Query("select distinct g from Group as g left join fetch g.permissions perms where g.id in (?1) ")
    List<Group> findByIds(Set<Integer> groupIds);

    @Query("select distinct g from Group as g left join fetch g.permissions perms where g.groupName in (?1) ")
    List<Group> findByNames(List<String> groupeNames);

    @Query("select distinct g from Group as g left join fetch g.permissions perms where g.groupType = ?1")
    List<Group> findByType(GroupType type);

    @Query("select g from Group as g left join fetch g.permissions perms where g.groupName =?1")
    Group findByGroupName(String name);
}
