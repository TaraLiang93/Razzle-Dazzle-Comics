package com.data.api.containers;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.googlecode.objectify.LoadResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/4/16.
 */
public class ResultContainer<T> implements Container<T> {

    LoadResult<T> result;
    public ResultContainer( LoadResult<T> result){
        this.result = result;
    }

    @Override
    public List<T> getList() throws FetchException{
        if( result == null){
            throw new FetchException("ResultContainer result null");
        }
        List<T> resultList = new ArrayList<>();
        resultList.add(result.now());
        return resultList;
    }

    @Override
    public T getResult() throws FetchException{
        if(result == null){
            throw new FetchException("ResultContainer result null");
        }
        return result.now();
    }
}
