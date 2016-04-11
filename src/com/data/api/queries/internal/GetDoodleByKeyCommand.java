package com.data.api.queries.internal;

import com.data.api.containers.ResultContainer;
import com.data.api.interfaces.Container;
import com.data.api.interfaces.Readable;
import com.data.creation.Doodle;
import com.google.appengine.api.datastore.Query.Filter;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.LoadResult;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 4/8/16.
 */
public class GetDoodleByKeyCommand extends Readable<Doodle> {

    Key<Doodle> doodleKey;

    public GetDoodleByKeyCommand( Key<Doodle> doodleKey){
        this.doodleKey = doodleKey;
    }

    @Override
    protected Filter getFilter() {
        return null;
    }

    @Override
    protected Class<Doodle> getType() {
        return null;
    }

    /**
     * fetch doodle by Key so overide the fetch
     * @return Container<Doodle> is a container containing the doodle
     */

    @Override
    public Container<Doodle> fetch(){ // TODO : do error validation, throw exceptions
        LoadResult<Doodle> doodleLoadResult = ofy().load().key(doodleKey);
        ResultContainer<Doodle> doodleResultContainer = new ResultContainer<>(doodleLoadResult);
        return doodleResultContainer;
    }



}
