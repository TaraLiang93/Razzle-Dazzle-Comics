package com.data.api;

/**
 * Created by drodrigues on 4/4/16.
 */
public interface FillDataCommand<T> {

    public void fillEntity(T entity);
}
