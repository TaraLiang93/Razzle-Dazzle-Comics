package com.model;

import com.data.creation.Canvas;
import com.googlecode.objectify.annotation.Id;

import java.util.List;

/**
 * Created by drodrigues on 4/8/16.
 */
public class SceneModel {

    @Id
    private Long id;

    private int index;

    private String setting;

    private Canvas canvasElement;

    private List<DialogueModel> dialogue;

    private String tinyMCEText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public Canvas getCanvasElement() {
        return canvasElement;
    }

    public void setCanvasElement(Canvas canvasElement) {
        this.canvasElement = canvasElement;
    }

    public List<DialogueModel> getDialogue() {
        return dialogue;
    }

    public void setDialogue(List<DialogueModel> dialogue) {
        this.dialogue = dialogue;
    }


    public String getTinyMCEText() {
        return tinyMCEText;
    }

    public void setTinyMCEText(String tinyMCEText) {
        this.tinyMCEText = tinyMCEText;
    }

    @Override
    public String toString() {
        return "\nSceneModel{" +
                "id=" + id +
                ", index=" + index +
                ", setting='" + setting + '\'' +
                ", \ndialogue=" + dialogue +
                ", tinyMCEText='" + tinyMCEText + "\'" +
                "\n}\n";
    }

}
