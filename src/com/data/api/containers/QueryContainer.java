package com.data.api.containers;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Container;
import com.googlecode.objectify.cmd.Query;

import java.util.List;

/**
 * Created by Zhenya on 4/1/16.
 */
public class QueryContainer<T> implements Container<T> {

    Query<T> query; // a QueryContainer will be initialized with a query

    /**
     * Constructor which takes in a QueryContainer
     * @param q the query object this container is supposed to wrap
     */
    public QueryContainer(Query<T> q){
        query = q;
    }

    public List<T> getList() throws  FetchException{
        if(query == null){
            throw new FetchException("QueryContainer query null");
        }
        return query.list();
    }

    //
    public void setResultSize( int size) throws FetchException{
        if(query == null){
            throw new FetchException("QueryContainer query null");
        }
        query.limit( size);
    }

    public void setDistinct( boolean distinct) throws FetchException{
        if(query == null){
            throw new FetchException("QueryContainer query null");
        }
        query.distinct( distinct );
    }

    public T getResult() throws FetchException{
        if(query == null){
            throw new FetchException("QueryContainer query null");
        }
        return query.first().now();
    }

}
