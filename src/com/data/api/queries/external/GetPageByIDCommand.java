package com.data.api.queries.external;

import com.data.api.containers.ResultContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.creation.Page;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 4/13/16.
 */
public class GetPageByIDCommand extends Readable
{
    Long pageId;
    public GetPageByIDCommand( Long pageId){
        this.pageId = pageId;
    }

    public GetPageByIDCommand( String strId){
        this.pageId = Long.parseLong(strId);
    }

    @Override
    protected Filter getFilter() throws FetchException {
        Filter filter;
        if( this.pageId != null) {
            filter = new FilterPredicate("id",
                    FilterOperator.EQUAL,
                    this.pageId);
        }
        else{
            throw new FetchException();
        }

        return filter;
    }

    @Override
    protected Class getType() {
        return Page.class;
    }

    @Override
    public Container fetch() throws  FetchException{ // TODO : do error validation, throw exceptions. Check OFY response codes
        if( this.pageId == null){
            throw new FetchException("GetPageByIDCommand pageId null");
        }
        LoadResult<Page> LoadResultOfID = ofy().load().type(getType()).id(this.pageId);
        ResultContainer<Page> resultContainer = new ResultContainer<Page>(LoadResultOfID);
        return resultContainer;
    }
}
