package com.data.structure;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class Flow {
    @Id
    Long flowId;

    // get the Key of a Doodle
    public Key<Flow> getKey() {
        return Key.create(Flow.class, flowId);
    }
}
