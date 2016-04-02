package com.data.api;

import com.googlecode.objectify.cmd.Query;

import java.util.List;

/**
 * Created by Zhenya on 4/1/16.
 */
public class Container<T>{

    Query<T> query; // a Container will be initialized with a query

    public Container(){}

    /**
     * Constructor which takes in a Container
     * @param q the query object this container is supposed to wrap
     */
    public Container( Query q){
        query = q;
    }

    public void setQuery(Query<T> query) {
        this.query = query;
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
