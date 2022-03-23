package com.maor.learn.spring.jwt.service.common;

import com.maor.learn.spring.jwt.model.common.EntityManager;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class EntityManagerServiceImpl<K extends Serializable & Comparable<K>, E extends EntityManager<K, ?>> implements EntityManagerService<K, E> {

    private final Class<E> objectClass;


    private final JpaRepository<E, K> repository;

    @SuppressWarnings("unchecked")
    public EntityManagerServiceImpl(JpaRepository<E, K> repository) {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.objectClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
        this.repository = repository;
    }

    public void save(E entity) throws ServiceException {
        repository.saveAndFlush(entity);
    }

    public void saveAll(Iterable<E> entities) throws ServiceException {
        repository.saveAll(entities);
    }

    public void update(E entity) throws ServiceException {
        save(entity);
    }

    public void create(E entity) throws ServiceException {
        save(entity);
    }

    public void delete(E entity) throws ServiceException {
        repository.delete(entity);
    }

    public E getById(K id) {
        return repository.getById(id);
    }

    public List<E> list() {
        return repository.findAll();
    }

    public Long count() {
        return repository.count();
    }

    public void flush() {
        repository.flush();
    }

    public Class<E> getObjectClass() {
        return objectClass;
    }
}
