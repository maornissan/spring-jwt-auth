package com.maor.learn.spring.jwt.service;

import com.maor.learn.spring.jwt.model.User;
import com.maor.learn.spring.jwt.service.common.EntityManagerService;
import org.hibernate.service.spi.ServiceException;

import java.util.List;

public interface UserService extends EntityManagerService<Long, User> {

    User getByUserName(String userName) throws ServiceException;

    List<User> listUser() throws ServiceException;

    User getById(Long id);

    void saveOrUpdate(User user) throws ServiceException;

}
