package com.data.api;

import com.googlecode.objectify.Key;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhenya on 4/4/16.
 */
public class MapContainer <T> implements Container<T>{

    Map<Key<T>,T> map;
    public MapContainer (Map<Key<T>, T> map){
        this.map = map;
    }

    @Override
    public List<T> getList() {
        /**
         * exeception will be thrown when the map is accessed it there are errors
         */
        List<T> listOfEntities = new ArrayList<>();
        for (Map.Entry<Key<T>, T> entity : this.map.entrySet()){
            listOfEntities.add( entity.getValue() ); //get value is of the type T
        }
        return listOfEntities;
    }

    @Override
    public T getResult() {
        /**
         * gets the first entity that the iterator will return
         */
        Map.Entry<Key<T>, T> entity = this.map.entrySet().iterator().next();
        return entity.getValue();
    }
}
