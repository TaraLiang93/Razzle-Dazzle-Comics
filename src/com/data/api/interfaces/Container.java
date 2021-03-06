package com.data.api.interfaces;

import com.data.api.exceptions.FetchException;

import java.util.List;

/**
 * Created by Zhenya on 4/4/16.
 */
public interface Container<T> {
    List<T> getList() throws FetchException;
    T getResult() throws FetchException;

}
