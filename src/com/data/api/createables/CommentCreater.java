package com.data.api.createables;

import com.data.UserData;
import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.api.interfaces.Readable;
import com.data.api.queries.external.GetUserDataByUserCommand;
import com.data.creation.Comment;
import com.google.appengine.api.users.User;

/**
 * Created by Zhenya on 4/26/16.
 */
public class CommentCreater extends Createable<Comment> {
    String commentText;
    User user;
    int index;

    public CommentCreater( User user, String commentText, int index ){
        this.user = user;
        this.commentText = commentText;
        this.index = index;

    }

    @Override
    protected Comment getEntity() throws CreateException, FetchException {
        if(commentText == null || commentText.equals("") || user == null){
            throw new CreateException("comment invalid");
        }
        //create comment
        Comment comment = new Comment();
        //set text
        comment.setComment(commentText);

        //get the userData from user
        Readable<UserData> userDataReadable = new GetUserDataByUserCommand(user);
        UserData userData = userDataReadable.fetch().getResult();
        //get UserDataKey from userData
        comment.setUserData(userData.getKey());

        if(index>=0){
            comment.setIndex(index);
        }

        return comment;
    }
}
