package com.data.api;

import com.googlecode.objectify.Key;

import java.util.List;
import java.util.Map;

/**
 * Created by Zhenya on 4/4/16.
 */
public class MapContainer <T> implements Container<T>{

    List<T> listOfEntities;
    public MapContainer (Map<Key<T>, T> map){

        for (Map.Entry<Key<T>, T> entity : map.entrySet()){
            listOfEntities.add( entity.getValue() ); //get value is of the type T
        }
    }

    @Override
    public List<T> getList() {
        return listOfEntities;
    }

    @Override
    public T getResult() {
        if( listOfEntities != null){
            return listOfEntities.get(0);
        }
        else{
            //perhaps throw exception?
            return null;
        }
    }
}
