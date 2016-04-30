package com.data.api.createables;

import com.data.api.exceptions.CreateException;
import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Createable;
import com.data.creation.Page;

/**
 * Created by Zhenya on 4/13/16.
 */
public class PageCreater extends Createable<Page> {

    private String title;
    private String summary;

    public PageCreater(){
        this( null);
    }

    public PageCreater( String title){
        this(title, null);
    }


    public PageCreater(String title, String summary){
        this.title = title;
        this.summary = summary;
    }


    @Override
    protected Page getEntity() throws CreateException, FetchException{

        Page page = new Page(title, summary);


        return page;
    }
}
