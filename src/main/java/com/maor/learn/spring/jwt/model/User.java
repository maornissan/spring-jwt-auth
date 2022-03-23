package com.maor.learn.spring.jwt.model;

import com.maor.learn.spring.jwt.model.common.EntityManager;
import com.maor.learn.spring.jwt.model.common.audit.AuditListener;
import com.maor.learn.spring.jwt.model.common.audit.AuditSection;
import com.maor.learn.spring.jwt.model.common.audit.Auditable;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditListener.class)
@Table(name = "USERS", uniqueConstraints = @UniqueConstraint(columnNames = {"USER_ID", "USERNAME"}))
public class User extends EntityManager<Long, User> implements Auditable {

    @Id
    @Column(name = "USER_ID", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "USER_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Embedded
    private AuditSection auditSection = new AuditSection();
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_ACCESS")
    private Date lastAccess;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOGIN_ACCESS")
    private Date loginTime;
    @Column(name = "ACTIVE")
    private boolean active = true;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "USER_GROUP", joinColumns = {
            @JoinColumn(name = "USER_ID", nullable = false, updatable = false)}
            ,
            inverseJoinColumns = {@JoinColumn(name = "GROUP_ID",
                    nullable = false, updatable = false)}
    )
    @Cascade({
            org.hibernate.annotations.CascadeType.DETACH,
            org.hibernate.annotations.CascadeType.LOCK,
            org.hibernate.annotations.CascadeType.REFRESH,
            org.hibernate.annotations.CascadeType.REPLICATE

    })
    private List<Group> groups = new ArrayList<Group>();

    public User() {

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public AuditSection getAuditSection() {
        return auditSection;
    }

    @Override
    public void setAuditSection(AuditSection auditSection) {
        this.auditSection = auditSection;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
