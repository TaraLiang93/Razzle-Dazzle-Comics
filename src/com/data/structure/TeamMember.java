package com.data.structure;

import com.data.UserData;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.api.queries.internal.GetEntityFromKeyCommand;
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

    @Index
    String userStringId;

    List<Key<RoleType>> roleList;

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

    public void setUserDataKey(Key<UserData> userDataKey) throws FetchException {
        this.userDataKey = userDataKey;
        Readable<UserData> userReadable = new GetEntityFromKeyCommand<>(this.userDataKey);
        userStringId = userReadable.fetch().getResult().getUserId();
    }

    public Long getTeamMemberId() {
        return teamMemberId;
    }

    public void setTeamMemberId(Long teamMemberId) {
        this.teamMemberId = teamMemberId;
    }

    public List<Key<RoleType>> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Key<RoleType>> roleList) {
        this.roleList = roleList;
    }

    public UserData getUserData() throws FetchException{
        Readable<UserData> userDataReadable = new GetEntityFromKeyCommand<>(userDataKey);
        return userDataReadable.fetch().getResult();
    }

    public void addRoleTypeToRoleTypeList( Key<RoleType> roleTypeKey){

        this.roleList.add(roleTypeKey);

    }

    public String getUserStringId() {
        return userStringId;
    }

    public void setUserStringId(String userStringId) {
        this.userStringId = userStringId;
    }



}
