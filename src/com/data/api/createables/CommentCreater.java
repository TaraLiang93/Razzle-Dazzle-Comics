package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.creation.Comment;

/**
 * Created by drodrigues on 4/26/16.
 */
public class CommentCreater extends Createable<Comment> {



    private String comment;

    public CommentCreater(String comment){
        this.comment = comment;
    }

    @Override
    protected Comment getEntity() throws CreateException, FetchException {

        Comment comm = new Comment(comment);

        return comm;
    }
}
