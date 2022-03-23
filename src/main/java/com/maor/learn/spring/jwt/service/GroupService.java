package com.maor.learn.spring.jwt.service;

import com.maor.learn.spring.jwt.model.Group;
import com.maor.learn.spring.jwt.model.GroupType;
import com.maor.learn.spring.jwt.model.User;
import com.maor.learn.spring.jwt.service.common.EntityManagerService;
import org.hibernate.service.spi.ServiceException;

import java.util.List;
import java.util.Set;

public interface GroupService extends EntityManagerService<Long, Group> {

    List<Group> listGroups(GroupType groupType) throws ServiceException;

    List<Group> listGroupsByIds(Set<Integer> ids) throws ServiceException;

    List<Group> listGroupsByNames(List<String> names) throws ServiceException;

    Group findByName(String groupName) throws ServiceException;

}
