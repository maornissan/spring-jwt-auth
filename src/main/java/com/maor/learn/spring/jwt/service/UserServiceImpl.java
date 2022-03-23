package com.maor.learn.spring.jwt.service;

import com.maor.learn.spring.jwt.model.User;
import com.maor.learn.spring.jwt.repository.UserRepository;
import com.maor.learn.spring.jwt.service.common.EntityManagerServiceImpl;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl extends EntityManagerServiceImpl<Long, User> implements UserService {

    @Autowired
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this. userRepository = userRepository;
    }
    @Override
    public User getByUserName(String userName) throws ServiceException {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> listUser() throws ServiceException {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public void saveOrUpdate(User user) throws ServiceException {
        userRepository.save(user);
    }
}
