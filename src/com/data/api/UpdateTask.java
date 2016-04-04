package com.data.api;

import java.util.List;

/**
 * Created by drodrigues on 4/4/16.
 */
public interface UpdateTask<T> {
    public List<T> update(Container<T> entity);
}
