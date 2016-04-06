package com.data.creation;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class Canvas {

    @Id
    Long canvasId;

    public Canvas(){

    }
}
