package com.data.api.createables.fillCommands;

import com.data.UserData;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.FillDataCommand;
import com.data.api.queries.external.GetPageByIDCommand;
import com.data.api.queries.external.GetUserDataByUserCommand;
import com.data.creation.Comment;
import com.data.creation.Page;
import com.google.appengine.api.users.User;

/**
 * Created by drodrigues on 4/27/16.
 */
public class CommentFillCommand implements FillDataCommand<Comment> {


    private User user;
    private String pageID;


    public CommentFillCommand(User user, String pageID){
        this.user = user;
        this.pageID = pageID;
    }

    @Override
    public void fillEntity(Comment entity) throws CreateException {

        if(user == null || pageID == null) throw new CreateException("User and Page ID must be provided to add comment");
        else if(entity == null) throw new CreateException("Comment Entity cannot be null!");

        try {
            Container<UserData>  getUser = new GetUserDataByUserCommand(user).fetch();
            Container<Page> getPage = new GetPageByIDCommand(pageID).fetch();

            UserData data = getUser.getResult();
            Page page = getPage.getResult();

            entity.setUserData(data.getKey());
            entity.setIndex(page.getComments().size()); // If n comments numbered 0...n-1, the next comment index is n

        } catch (FetchException e) {
            e.printStackTrace();
            throw new CreateException("Error fetching the required data to add comment --> User : "  + user + "\t PageID : " + pageID);
        }
    }
}
