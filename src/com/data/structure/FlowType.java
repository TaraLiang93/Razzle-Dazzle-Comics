package com.data.structure;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by Zhenya on 4/25/16.
 */
@Entity
public class FlowType {
    @Id
    Long flowTypeId;

    String flowTypeName;

    public FlowType(){

    }

    public Long getFlowTypeId() {
        return flowTypeId;
    }

    public void setFlowTypeId(Long flowTypeId) {
        this.flowTypeId = flowTypeId;
    }

    public Key<FlowType> getKey(){
        return Key.create(FlowType.class, flowTypeId);
    }

    public String getFlowTypeName() {
        return flowTypeName;
    }

    public void setFlowTypeName(String flowTypeName) {
        this.flowTypeName = flowTypeName;
    }
}
