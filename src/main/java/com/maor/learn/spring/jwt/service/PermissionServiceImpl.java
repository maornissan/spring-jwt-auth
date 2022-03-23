package com.maor.learn.spring.jwt.service;

import com.maor.learn.spring.jwt.model.Group;
import com.maor.learn.spring.jwt.model.GroupType;
import com.maor.learn.spring.jwt.model.Permission;
import com.maor.learn.spring.jwt.repository.PermissionRepository;
import com.maor.learn.spring.jwt.service.common.EntityManagerServiceImpl;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("permissionService")
public class PermissionServiceImpl extends EntityManagerServiceImpl<Long, Permission> implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        super(permissionRepository);
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<Permission> getByName() {
        return permissionRepository.findAll();
    }

    @Override
    public List<Permission> listPermission() throws ServiceException {
        return permissionRepository.findAll();
    }

    @Override
    public Permission getById(Long permissionId) {
        return permissionRepository.getById(permissionId);
    }

    @Override
    public List<Permission> getPermissions(List<Long> groupIds) throws ServiceException {
        return permissionRepository.findByGroups(new HashSet<>(groupIds));
    }

    @Override
    public void deletePermission(Permission permission) throws ServiceException {
        permissionRepository.delete(permission);
    }

    @Override
    public void removePermission(Permission permission, Group group) throws ServiceException {
        List<Permission> permissionList = listPermission();
        for(Permission perm : permissionList) {
            for(Group grp : perm.getGroups()) {
                if(grp.equals(group) && perm.equals(permission))
                    permissionRepository.delete(permission);
            }
        }
    }
}
