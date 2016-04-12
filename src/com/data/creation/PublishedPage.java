package com.data.creation;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class PublishedPage {
    @Id
    Long publishedPageId;


    public Key<PublishedPage> getKey(){
        return Key.create(PublishedPage.class, publishedPageId);
    }
}
