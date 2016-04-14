package com.data.api.queries.external;

import com.data.api.containers.ResultContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.creation.Chapter;
import com.google.appengine.api.datastore.Query.Filter;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 4/13/16.
 */
public class GetChapterByIDCommand extends Readable {

    Long chapterId;

    public GetChapterByIDCommand( Long chapterId){
        this.chapterId = chapterId;
    }

    public GetChapterByIDCommand( String chapterId){
        try {
            this.chapterId = Long.parseLong(chapterId);
        }
        catch ( NumberFormatException ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected Filter getFilter() throws FetchException {
        return null;
    }

    @Override
    protected Class getType() {
        return Chapter.class;
    }

    @Override
    public Container fetch() throws  FetchException{ // TODO : do error validation, throw exceptions. Check OFY response codes
        if( this.chapterId == null){
            throw new FetchException("GetChapterByIDCommand chapterID null");
        }
        LoadResult<Chapter> LoadResultOfID = ofy().load().type(getType()).id(this.chapterId);
        ResultContainer<Chapter> resultContainer = new ResultContainer<Chapter>(LoadResultOfID);
        return resultContainer;
    }
}
