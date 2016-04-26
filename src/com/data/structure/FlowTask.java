package com.data.structure;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class FlowTask {
    @Id
    Long flowTaskId;

    String flowTaskName;
    int index;
    Key<FlowType> flowTypeKey;

    public Long getFlowTaskId() {
        return flowTaskId;
    }

    public void setFlowTaskId(Long flowTaskId) {
        this.flowTaskId = flowTaskId;
    }

    public String getFlowTaskName() {
        return flowTaskName;
    }

    public void setFlowTaskName(String flowTaskName) {
        this.flowTaskName = flowTaskName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Key<FlowType> getFlowTypeKey() {
        return flowTypeKey;
    }

    public void setFlowTypeKey(Key<FlowType> flowTypeKey) {
        this.flowTypeKey = flowTypeKey;
    }
}
