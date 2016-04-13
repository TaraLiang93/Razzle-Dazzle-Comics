package com.data.api.queries.external;

import com.data.api.containers.ResultContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.creation.Scene;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 4/13/16.
 */
public class GetSceneByIDCommand extends Readable {

    Long sceneId;

    public GetSceneByIDCommand(Long sceneId){
        this.sceneId = sceneId;
    }

    public GetSceneByIDCommand( String strId ){
        this.sceneId = Long.parseLong( strId );
    }

    @Override
    protected Filter getFilter() throws FetchException {
        Filter filter;
        if( this.sceneId != null) {
            filter = new FilterPredicate("id",
                    FilterOperator.EQUAL,
                    this.sceneId);
        }
        else{
            throw new FetchException();
        }

        return filter;
    }

    @Override
    protected Class getType() {
        return Scene.class;
    }

    @Override
    public Container fetch() throws  FetchException{ // TODO : do error validation, throw exceptions. Check OFY response codes
        if( this.sceneId == null){
            throw new FetchException("GetSceneByIDCommand sceneID null");
        }
        LoadResult<Scene> LoadResultOfID = ofy().load().type(getType()).id(this.sceneId);
        ResultContainer<Scene> resultContainer = new ResultContainer<Scene>(LoadResultOfID);
        return resultContainer;
    }

}
