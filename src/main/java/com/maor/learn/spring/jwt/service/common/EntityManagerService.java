package com.maor.learn.spring.jwt.service.common;


import com.maor.learn.spring.jwt.model.common.EntityManager;
import org.hibernate.service.spi.ServiceException;

import java.io.Serializable;
import java.util.List;

public interface EntityManagerService<K extends Serializable & Comparable<K>, E extends EntityManager<K, ?>> {


    void save(E entity) throws ServiceException;

    void saveAll(Iterable<E> entities) throws ServiceException;

    void update(E entity) throws ServiceException;

    void create(E entity) throws ServiceException;

    void delete(E entity) throws ServiceException;

    E getById(K id);

    List<E> list();

    Long count();

    void flush();

}
