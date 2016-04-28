package com.data.api.containers;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drodrigues on 4/28/16.
 */
public class EntityContainer<T> implements Container<T> {

    private T entity;

    public EntityContainer(T entity){
        this.entity = entity;
    }

    @Override
    public List<T> getList() throws FetchException {
        List<T> newList = new ArrayList<>();
        newList.add(getResult());
        return newList;
    }

    @Override
    public T getResult() throws FetchException {
        if(entity == null) throw new FetchException("Entity cannot be null");
        return entity;
    }
}
