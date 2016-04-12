package com.data.api.createables.fillCommands;

import com.data.api.interfaces.FillDataCommand;

/**
 * Created by Zhenya on 4/5/16.
 */
public class NoWork<T> implements FillDataCommand<T> {

    @Override
    public void fillEntity(T entity) {
        System.out.println("Not doing anything");
    }
}
