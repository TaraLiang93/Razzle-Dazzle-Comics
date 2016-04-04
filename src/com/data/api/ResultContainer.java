package com.data.api;

import com.googlecode.objectify.LoadResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenya on 4/4/16.
 */
public class ResultContainer<T> implements Container<T>{

    LoadResult<T> result;
    public ResultContainer( LoadResult<T> result){
        this.result = result;
    }

    @Override
    public List<T> getList() {
        List<T> resultList = new ArrayList<>();
        resultList.add(result.now());
        return resultList;
    }

    @Override
    public T getResult() {
        return result.now();
    }
}
