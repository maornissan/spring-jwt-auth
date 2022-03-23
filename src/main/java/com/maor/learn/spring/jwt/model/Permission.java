package com.maor.learn.spring.jwt.model;

import com.maor.learn.spring.jwt.model.common.EntityManager;
import com.maor.learn.spring.jwt.model.common.audit.AuditListener;
import com.maor.learn.spring.jwt.model.common.audit.AuditSection;
import com.maor.learn.spring.jwt.model.common.audit.Auditable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditListener.class)
@Table(name = "PERMISSION")
public class Permission extends EntityManager<Long, Permission> implements Auditable {
    private static final long serialVersionUID = 813468144435420748L;

    @Id
    @Column(name = "PERMISSION_ID", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PERMISSION_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;
    @Column(name = "PERMISSION_TYPE")
    @Enumerated(value = EnumType.STRING)
    private PermissionType permissionType;
    @Column(name = "PERMISSION_NAME", unique = true)
    private String permissionName;
    @ManyToMany(mappedBy = "permissions")
    private List<Group> groups = new ArrayList<Group>();
    @Embedded
    private AuditSection auditSection = new AuditSection();

    public Permission() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
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
