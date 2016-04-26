package com.data.api.createables;

import com.data.UserData;
import com.data.api.createables.fillCommands.TeamMemberFillCommand;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.api.interfaces.Readable;
import com.data.api.queries.external.GetUserDataByUserCommand;
import com.data.creation.Chapter;
import com.data.structure.TeamMember;
import com.google.appengine.api.users.User;

/**
 * Created by Zhenya on 4/9/16.
 */
public class ChapterCreater extends Createable<Chapter> {

    String title;
    String chapterString;
    String description;
    User user;

// TODO: when creating a chapted add User as a Team Member
    public ChapterCreater(User user, String title, String chapterString){
        this(user, title, chapterString, null);
    }


    public ChapterCreater(User user, String title, String chapterString, String description){

        this.title = title;
        this.chapterString = chapterString;
        this.description = description;
        this.user = user;


        // Image service factory makeImageFromFileName
        // blob takes byte array
        // use image service
    }

    @Override
    protected Chapter getEntity() throws CreateException, FetchException{ // TODO : throw exceptions, do validation

        // create chapter and fill with initial data
        Chapter chapter = new Chapter();

        if( this.title == null || this.title.equals("") ||
                this.chapterString == null || this.chapterString.equals("")){
            throw new CreateException();
        }
        else{
            chapter.setTitle(this.title);
            chapter.setDescription(this.description);
        }

        if( this.description != null &&  !(this.description.equals(""))) {
            chapter.setDescription(this.description);
        }

        /**
         * add User as TeamMember when creating chapter
         */

        // Get UserData from User
        Readable<UserData> userDataReadable = new GetUserDataByUserCommand(this.user);
        UserData userData = userDataReadable.fetch().getResult();

        //create Team Member
        Createable<TeamMember> teamMemberCreateable = new TeamMemberCreater( userData.getKey() );
        TeamMember teamMember = teamMemberCreateable.createEntity(new TeamMemberFillCommand());


        chapter.addTeamMemberToTeamMemberList(teamMember.getKey());

        return chapter;

    }
}
