package com.data.structure;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class RoleType {

    @Id
    Long roleTypeId;

    String roleTypeName;

    public RoleType ( String roleTypeName ) {
        this.roleTypeName = roleTypeName;
    }

    public Long getRoleTypeIdId() {
        return roleTypeId;
    }

    public void setRoleTypeId(Long roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    public String getRoleTypeName() {
        return roleTypeName;
    }

    public void setRoleTypeName(String roleTypeName) {
        this.roleTypeName = roleTypeName;
    }

    public Key<Role> getKey(){
        return Key.create(Role.class, roleTypeId);
    }




}
