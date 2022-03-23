package com.maor.learn.spring.jwt.service;

import com.maor.learn.spring.jwt.model.Group;
import com.maor.learn.spring.jwt.model.GroupType;
import com.maor.learn.spring.jwt.model.Permission;
import com.maor.learn.spring.jwt.service.common.EntityManagerService;
import org.hibernate.service.spi.ServiceException;

import java.util.List;
import java.util.Set;

public interface PermissionService extends EntityManagerService<Long, Permission> {

    List<Permission> getByName();

    List<Permission> listPermission() throws ServiceException;

    Permission getById(Long permissionId);

    List<Permission> getPermissions(List<Long> groupIds) throws ServiceException;

    void deletePermission(Permission permission) throws ServiceException;


    void removePermission(Permission permission, Group group) throws ServiceException;

}
