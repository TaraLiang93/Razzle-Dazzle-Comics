package com.data.api;

import java.util.List;

/**
 * Created by Zhenya on 4/4/16.
 */
public interface Container<T> {
    List<T> getList();
    T getResult();

}