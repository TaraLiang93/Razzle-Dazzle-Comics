package com.model;

/**
 * Created by drodrigues on 4/8/16.
 */
public class DialogueModel {

    private Long id;

    private String dialogue;

    private int index;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDialogue() {
        return dialogue;
    }

    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "DialogueModel{" +
                "id=" + id +
                ", dialogue='" + dialogue + '\'' +
                ", index=" + index +
                "}\n";
    }
}
