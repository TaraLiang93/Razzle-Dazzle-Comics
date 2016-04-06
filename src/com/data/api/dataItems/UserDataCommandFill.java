package com.data.api.dataItems;

import com.data.UserData;
import com.data.api.FillDataCommand;
import com.data.creation.Doodle;
import com.data.creation.Scribble;
import com.data.structure.Bookmark;
import com.data.structure.Flow;
import com.data.structure.Series;
import com.data.structure.Tag;
import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/5/16.
 */
public class UserDataCommandFill implements FillDataCommand<UserData>{


    /**
     * Default constructor with no parameters
     */
    public UserDataCommandFill(){

    }


    /**
     * When fill Entity
     * @param entity
     */
    @Override
    public void fillEntity(UserData entity) {
        //TODO: Get default flows
        //TODO: Do a query for default flows
        List<Key<Flow>> defaultFlowkeys = new ArrayList<Key<Flow>>();
        entity.setFlowList(defaultFlowkeys);
    }

}
