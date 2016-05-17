package com.data.api.queries.external;

import com.data.api.containers.MapContainer;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.creation.Chapter;
import com.data.creation.Page;
import com.data.structure.FlowTask;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.googlecode.objectify.Key;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhenya on 4/28/16.
 */
public class GetPagesInDoneFlowTaskCommand extends Readable{
    Filter filter;
    Long chapterId;

    //We can make it generic to take the flowTypeID if we wanted to
    public GetPagesInDoneFlowTaskCommand( Long chapterId){
        this.chapterId = chapterId;
    }

    public GetPagesInDoneFlowTaskCommand( String chapterId){
        this.chapterId = Long.parseLong( chapterId );
    }

    //Filter won't be used
    @Override
    protected Filter getFilter() throws FetchException {

        //create filter
        filter = new FilterPredicate("flowTaskName",
                FilterOperator.EQUAL,
                "Done Task");
        return filter;

    }

    @Override
    protected Class<Page> getType() {
        return Page.class;
    }

    @Override
    public MapContainer fetch() throws FetchException{
        //Get the chapter Entity
        Readable<Chapter> chapterReadable = new GetChapterByIDCommand(chapterId);
        Chapter chapter = chapterReadable.fetch().getResult();

        //Get Id of the done flow task
        FlowTask doneFlowTask = null;
        for( FlowTask flowTask : chapter.getFlow().getFlowTasks() ){
            if( flowTask.getIndex() == 4 ){
                doneFlowTask = flowTask;
            }
        }
        if( doneFlowTask == null){
            throw new FetchException("done flow task is not found");
        }
        Map<Key<Page>, Page> pageMap = new HashMap<Key<Page>, Page>();


        for( Page page : chapter.getPages()) {
            //if page's flowTask is the doneFlowTask
            if( page.getFlowTask()!=null ) {
                if (page.getFlowTaskEntity().getFlowTaskId() == doneFlowTask.getFlowTaskId()) {
                    pageMap.put(page.getKey(), page);
                }
            }
        }


        MapContainer mapContainer = new MapContainer( pageMap);

        return mapContainer;
    }







}
