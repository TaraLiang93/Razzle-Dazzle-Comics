package com.data.api.interfaces;

import com.data.api.exceptions.CreateException;

/**
 * Created by drodrigues on 4/4/16.
 */
public interface FillDataCommand<T> {

    public void fillEntity(T entity) throws CreateException;
}
