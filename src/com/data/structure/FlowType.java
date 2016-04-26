package com.data.structure;

import com.googlecode.objectify.annotation.Id;

/**
 * Created by Zhenya on 4/25/16.
 */
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

}
