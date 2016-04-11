package com.data.api.createables.fillCommands;

import com.data.UserData;
import com.data.api.interfaces.FillDataCommand;
import com.data.structure.Flow;
import com.googlecode.objectify.Key;

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
        List<Key<Flow>> defaultFlowkeys = new ArrayList<Key<Flow>>(); //TODO : Move flow list inside creating UserData
        entity.setFlowList(defaultFlowkeys);
    }

}
