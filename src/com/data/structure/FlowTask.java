package com.data.structure;

import com.data.api.exceptions.FetchException;
import com.data.api.interfaces.Readable;
import com.data.api.queries.internal.GetEntityFromKeyCommand;
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
    Key<FlowTask> nextTask;
    Key<FlowTask> prevTask;

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

    public Key<FlowTask> getNextTask() {
        return nextTask;
    }

    public void setNextTask(Key<FlowTask> nextTask) {
        this.nextTask = nextTask;
    }

    public Key<FlowTask> getPrevTask() {
        return prevTask;
    }

    public void setPrevTask(Key<FlowTask> prevTask) {
        this.prevTask = prevTask;
    }

    public Key<FlowTask> getKey(){
        return Key.create(FlowTask.class, flowTaskId);
    }

    public FlowType getFlowType() throws FetchException{
        Readable<FlowType> flowTypeReadable = new GetEntityFromKeyCommand<>(this.flowTypeKey);
        return flowTypeReadable.fetch().getResult();
    }
}
