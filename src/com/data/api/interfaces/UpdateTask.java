package com.data.api.interfaces;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.exceptions.UpdateException;

import java.util.List;

/**
 * Created by drodrigues on 4/4/16.
 */
public interface UpdateTask<T> {
    public List<T> update(Container<T> entity) throws UpdateException, FetchException, CreateException;
}
