package com.data.api.queries.external;

import com.data.api.containers.ResultContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.creation.Canvas;
import com.data.creation.Chapter;
import com.google.appengine.api.datastore.Query;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 5/17/16.
 */
public class GetCanvasByIDCommand extends Readable {

    Long canvasID;
    public GetCanvasByIDCommand(Long canvasID){
        this.canvasID = canvasID;
    }

    public  GetCanvasByIDCommand( String canvasID){
        try {
            this.canvasID = Long.parseLong(canvasID);
        }
        catch ( NumberFormatException ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected Query.Filter getFilter() throws FetchException {
        return null;
    }

    @Override
    protected Class getType() {
        return Canvas.class;
    }

    @Override
    public Container fetch() throws  FetchException{ // TODO : do error validation, throw exceptions. Check OFY response codes
        if( this.canvasID == null){
            throw new FetchException("GetCanvasByIDCommand canvasID null");
        }
        LoadResult<Chapter> LoadResultOfID = ofy().load().type(getType()).id(this.canvasID);
        ResultContainer<Chapter> resultContainer = new ResultContainer<Chapter>(LoadResultOfID);
        return resultContainer;
    }
}
