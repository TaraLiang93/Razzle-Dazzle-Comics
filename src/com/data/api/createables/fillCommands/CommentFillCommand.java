package com.data.api.createables.fillCommands;

import com.data.api.exceptions.CreateException;
import com.data.api.interfaces.FillDataCommand;
import com.data.creation.Comment;

/**
 * Created by Zhenya on 4/26/16.
 */
public class CommentFillCommand implements FillDataCommand<Comment> {

    public CommentFillCommand(){

    }

    @Override
    public void fillEntity(Comment entity) throws CreateException {
        //nothing becaues all fields can be passed in with the creater
    }

}
