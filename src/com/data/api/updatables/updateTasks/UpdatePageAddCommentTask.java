package com.data.api.updatables.updateTasks;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.api.interfaces.UpdateTask;
import com.data.api.queries.external.GetCommentByIdCommand;
import com.data.creation.Comment;
import com.data.creation.Page;
import com.google.appengine.api.users.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/26/16.
 */
public class UpdatePageAddCommentTask implements UpdateTask<Page> {

    User user;
    Long commentId;
    public UpdatePageAddCommentTask(User user, Long commentId){
        this.user = user;
        this.commentId = commentId;
    }

    public UpdatePageAddCommentTask(User user, String commentId){
        this.user = user;
        try {
            this.commentId = Long.parseLong(commentId);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
    }
    @Override
    public List<Page> update(Container<Page> entity) throws UpdateException, FetchException, CreateException {
        if( user == null || commentId < 0){
            throw  new UpdateException( " user null or comment id invalid");
        }

        Page pageToUpdate = entity.getResult();

        //Get the comment
        Readable<Comment> commentReadable = new GetCommentByIdCommand(commentId);
        Comment comment = commentReadable.fetch().getResult();

        // update page with comment
        pageToUpdate.addCommentToCommentList( comment.getKey() );

        //add page to list
        List<Page> pageList = new ArrayList<>();
        pageList.add(pageToUpdate);

        return pageList;

    }


}
