package com.data.api.updatables.updateTasks;

import com.data.UserData;
import com.data.api.createables.TeamMemberCreater;
import com.data.api.createables.fillCommands.TeamMemberFillCommand;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Createable;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.external.GetUserDataByUserNameCommand;
import com.data.creation.Chapter;
import com.data.structure.TeamMember;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/15/16.
 */
public class UpdateChapterAddTeamMemberTask implements UpdateTask<Chapter> {
    String userName; // the username of the user which is same as the gmail


    public UpdateChapterAddTeamMemberTask(String userName){
        this.userName = userName;
    }

    @Override
    public List<Chapter> update(Container<Chapter> entity) throws UpdateException, FetchException, CreateException {
        Chapter chapter = entity.getResult();
        if( this.userName == null || this.userName.equals("") ){
            throw new UpdateException("UpdateChapterAddTeamMemberTask userName is null");
        }
        else{//userName is Valid

            // get the userData with userName
            Readable<UserData> getUserData= new GetUserDataByUserNameCommand(userName);
            UserData userData = getUserData.fetch().getResult();

            if( userData == null){
                throw new UpdateException("userData null");
            }

            //Create the TeamMember object
            Createable<TeamMember> teamMemberCreateable = new TeamMemberCreater( userData.getKey());
            TeamMember teamMember = teamMemberCreateable.createEntity( new TeamMemberFillCommand());

            // add TeamMember to Chapter
            chapter.addTeamMemberToTeamMemberList(teamMember.getKey());
        }

        List<Chapter> chapterList = new ArrayList<>();
        chapterList.add(chapter);

        return chapterList;

    }
}
