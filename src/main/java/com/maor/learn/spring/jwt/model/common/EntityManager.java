/*
 * Copyright (c) 2020. Maor Nissan.
 * This is a private code, not opened source.
 */

package com.maor.learn.spring.jwt.model.common;

import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.text.Collator;
import java.util.Locale;


/**
 * <p>Base entity for object persistence's - JPA</p>
 *
 * @param <E> Type of the entity.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class EntityManager<K extends Serializable & Comparable<K>, E extends EntityManager<K, ?>>
        implements Serializable, Comparable<E> {

    public static final Collator DEFAULT_STRING_COLLATOR = Collator.getInstance(Locale.ENGLISH);
    private static final long serialVersionUID = -3988499137919591054L;

    static {
        DEFAULT_STRING_COLLATOR.setStrength(Collator.PRIMARY);
    }


    /**
     * Returns the value of the unique identifier.
     *
     * @return id
     */
    public abstract K getId();

    /**
     * Sets the value of the unique identifier.
     *
     * @param id id
     */
    public abstract void setId(K id);

    /**
     * Indicates whether the object has already been persisted or not.
     *
     * @return true if the object has not yet been persisted
     */
    public boolean isNew() {
        return getId() == null;
    }


    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }

        // the object can be proxyfied so we use Hibernate.getClass () to output the real class.
        if (Hibernate.getClass(object) != Hibernate.getClass(this)) {
            return false;
        }

        EntityManager<K, E> entity = (EntityManager<K, E>) object;
        K id = getId();

        if (id == null) {
            return false;
        }

        return id.equals(entity.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;

        K id = getId();
        hash = 31 * hash + ((id == null) ? 0 : id.hashCode());

        return hash;
    }

    public int compareTo(E o) {
        if (this == o) {
            return 0;
        }
        return this.getId().compareTo(o.getId());
    }

    @Override
    public String toString() {

        return "entity." +
                Hibernate.getClass(this).getSimpleName() +
                "<" +
                getId() +
                "-" +
                super.toString() +
                ">";
    }

}
