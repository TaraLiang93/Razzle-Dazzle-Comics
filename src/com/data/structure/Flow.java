package com.data.structure;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class Flow {
    @Id
    Long flowId;

    String flowName;

    List<Key<FlowTask>> flowTasks;

    public Flow(){
        flowTasks  = new ArrayList<>();
    }

    // get the Key of a Doodle
    public Key<Flow> getKey() {
        return Key.create(Flow.class, flowId);
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public List<Key<FlowTask>> getFlowTasks() {
        return flowTasks;
    }

    public void setFlowTasks(List<Key<FlowTask>> flowTasks) {
        this.flowTasks = flowTasks;
    }

    public void addFlowTaskToFlowTaskList( Key<FlowTask> flowTaskKey){
        this.flowTasks.add(flowTaskKey);
    }
}
