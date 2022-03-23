package com.maor.learn.spring.jwt.service;

import com.maor.learn.spring.jwt.model.Group;
import com.maor.learn.spring.jwt.model.GroupType;
import com.maor.learn.spring.jwt.repository.GroupRepository;
import com.maor.learn.spring.jwt.service.common.EntityManagerServiceImpl;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("groupService")
public class GroupServiceImpl extends EntityManagerServiceImpl<Long, Group> implements GroupService {

    @Autowired
    GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        super(groupRepository);
        this.groupRepository = groupRepository;
    }
    @Override
    public List<Group> listGroups(GroupType groupType) throws ServiceException {
        return groupRepository.findByType(groupType);
    }

    @Override
    public List<Group> listGroupsByIds(Set<Integer> ids) throws ServiceException {
        return groupRepository.findByIds(ids);
    }

    @Override
    public List<Group> listGroupsByNames(List<String> names) throws ServiceException {
        return groupRepository.findByNames(names);
    }

    @Override
    public Group findByName(String groupName) throws ServiceException {
        return groupRepository.findByGroupName(groupName);
    }
}
