package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.UpdateTask;
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
        // get chapter
        Chapter chapter = entity.getResult();

        if (chapter == null || userName == null){
            throw new UpdateException("chapter or userName ID is null");
        }

        Key<TeamMember> teamMemberKeyToRemove = null;

        for( TeamMember teamMember : chapter.getTeamMembers() ){
            String teamMemberUserName = teamMember.getUserData().getUserName();
            if( this.userName.equals( teamMemberUserName ) ){ // if teamMember's UserName is the userName we are looking for
                teamMemberKeyToRemove = teamMember.getKey();
            }
        }

        if(teamMemberKeyToRemove !=null){
            chapter.getTeamMemberList().remove(teamMemberKeyToRemove);
        }

        List<Chapter> chapterList = new ArrayList<>();
        chapterList.add(chapter);

        return chapterList;
    }

}
