package com.data.api;

import com.googlecode.objectify.cmd.Query;

import java.util.List;

/**
 * Created by Zhenya on 4/1/16.
 */
public class QueryContainer<T> implements Container<T>{

    Query<T> query; // a QueryContainer will be initialized with a query

    /**
     * Constructor which takes in a QueryContainer
     * @param q the query object this container is supposed to wrap
     */
    public QueryContainer(Query<T> q){
        query = q;
    }

    public List<T> getList(){
        return query.list();
    }

    //
    public void setResultSize( int size){
        query.limit( size);
    }

    public void setDistinct( boolean distinct){
        query.distinct( distinct );
    }

    public T getResult(){
        return query.first().now();
    }

}
