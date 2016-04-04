package com.data.api.DoodleQueries;

import com.data.UserData;
import com.data.api.Container;
import com.data.api.Readable;
import com.data.api.ResultContainer;
import com.data.creation.Scribble;
import com.google.appengine.api.datastore.Query.*;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.LoadResult;

import java.util.List;
import java.util.Map;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by Zhenya on 4/4/16.
 */
public class GetScribblesCommand{

    Key<UserData> userDataKey;

    public GetScribblesCommand(Key<UserData> userDataKey){
        this.userDataKey = userDataKey;
    }

//    @Override
//    protected Filter getFilter() {
//        return null;
//    }
//
//    @Override
//    protected Class getType() {
//        return Scribble.class;
//    }

//    /**
//     *  fetch returns a
//     * @return
//     */
//    @Override
//    public Container fetch(){
//        UserData userData = ofy().load().key(userDataKey).now();
//        List<Key<Scribble>> scribbleList = userData.getScribbleList();
//        Map<Key<Scribble>, Scribble> ths = ofy().load().keys(scribbleList);
////
////        ResultContainer<Scribble> scribbleResultContainer = new ResultContainer<Scribble>(ths);
//    }
}
