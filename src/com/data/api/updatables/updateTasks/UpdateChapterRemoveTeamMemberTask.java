package com.data.api.updatables.updateTasks;

import com.data.UserData;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.external.GetUserDataByUserNameCommand;
import com.data.api.queries.internal.GetTeamMemberWithUserDataKeyCommand;
import com.data.creation.Chapter;
import com.data.structure.TeamMember;
import com.googlecode.objectify.Key;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/20/16.
 */
public class UpdateChapterRemoveTeamMemberTask implements UpdateTask<Chapter> {
    String userName;


    public UpdateChapterRemoveTeamMemberTask( String userName){
        try {
            this.userName = userName;
        }
        catch ( NumberFormatException ex){
            ex.printStackTrace();
        }
    }


    @Override
    public List<Chapter> update(Container<Chapter> entity) throws UpdateException, FetchException, CreateException {
        Chapter chapter = entity.getResult();

        if (chapter == null || userName == null){
            throw new UpdateException("chapter or userName ID is null");
        }

        // get the userData with userName
        Readable<UserData> getUserData= new GetUserDataByUserNameCommand(userName);
        UserData userData = getUserData.fetch().getResult();

        if( userData == null){
            throw new UpdateException("userData null");
        }


        //get TeamMember

        Readable<TeamMember> teamMemberReadable = new GetTeamMemberWithUserDataKeyCommand(userData.getKey());
        TeamMember teamMember = teamMemberReadable.fetch().getResult();
        Key<TeamMember> teamMemberKey = teamMember.getKey();
        chapter.getTeamMemberList().remove( teamMemberKey);
        // TODO: Make sure this actually removes a key from List<Key>


        List<Chapter> chapterList = new ArrayList<>();
        chapterList.add(chapter);

        return chapterList;
    }

}
