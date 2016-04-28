package com.data.api.queries.external;

import com.data.api.containers.ResultContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.creation.Comment;
import com.google.appengine.api.datastore.Query;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 4/26/16.
 */
public class GetCommentByIdCommand extends Readable<Comment> {

    Long commentId;

    public GetCommentByIdCommand( Long commentId){
        this.commentId = commentId;
    }

    public GetCommentByIdCommand( String commentId){
        try {
            this.commentId = Long.parseLong(commentId);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
    }

    //Not used can't filter on id for some reason
    @Override
    protected Query.Filter getFilter() throws FetchException {
        return null;
    }

    @Override
    protected Class getType() {
        return Comment.class;
    }

    @Override
    public Container fetch() throws  FetchException{ // TODO : do error validation, throw exceptions. Check OFY response codes

        if( this.commentId == null){
            throw new FetchException("GetComment ByIDCommand commentId null");
        }

        LoadResult<Comment> LoadResultOfID = ofy().load().type(getType()).id(this.commentId);
        ResultContainer<Comment> resultContainer = new ResultContainer<Comment>(LoadResultOfID);

        return resultContainer;

    }


}
