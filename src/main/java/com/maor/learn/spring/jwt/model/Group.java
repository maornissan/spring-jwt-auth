package com.maor.learn.spring.jwt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maor.learn.spring.jwt.model.common.EntityManager;
import com.maor.learn.spring.jwt.model.common.audit.AuditListener;
import com.maor.learn.spring.jwt.model.common.audit.AuditSection;
import com.maor.learn.spring.jwt.model.common.audit.Auditable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditListener.class)
@Table(name = "USER_GROUPS", indexes = {
        @Index(name = "USERS_GROUP_GROUP_TYPE", columnList = "GROUP_TYPE")})
public class Group extends EntityManager<Long, Group> implements Auditable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "GROUP_ID", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "GROUP_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;
    @Column(name = "GROUP_TYPE")
    @Enumerated(value = EnumType.STRING)
    private GroupType groupType;
    @Column(name = "GROUP_NAME", unique = true)
    private String groupName;
    @JsonIgnore
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "PERMISSION_GROUP",
            joinColumns = @JoinColumn(name = "GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID")
    )
    private Set<Permission> permissions = new HashSet<Permission>();
    @Embedded
    private AuditSection auditSection = new AuditSection();

    public Group() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public AuditSection getAuditSection() {
        return auditSection;
    }

    @Override
    public void setAuditSection(AuditSection auditSection) {
        this.auditSection = auditSection;
    }
}
