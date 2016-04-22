package com.data.structure;

import com.data.UserData;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class TeamMember {
    @Id
    Long teamMemberId;

    @Index
    Key<UserData> userDataKey;

    List<Key<Role>> roleList;

    public TeamMember(){
        userDataKey = null;
        roleList = new ArrayList<>();
    }


    public Key<TeamMember> getKey(){
        return Key.create(TeamMember.class, teamMemberId);
    }

    public Key<UserData> getUserDataKey() {
        return userDataKey;
    }

    public void setUserDataKey(Key<UserData> userDataKey) {
        this.userDataKey = userDataKey;
    }

    public Long getTeamMemberId() {
        return teamMemberId;
    }

    public void setTeamMemberId(Long teamMemberId) {
        this.teamMemberId = teamMemberId;
    }

    public List<Key<Role>> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Key<Role>> roleList) {
        this.roleList = roleList;
    }
}
