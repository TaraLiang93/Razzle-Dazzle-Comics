package com.data.creation;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class Scene {

    @Id
    private Long id;

    private int index;

    private String setting;

    private String tinyMCEText;

    private Key<Canvas> canvasElement;

    private List<Key<Dialogue>> dialogueList;

    private List<Dialogue> dialogue;

    static int count = 0;

    public Scene(String setting){
        this.setting = setting;
        this.index = count++;

        dialogue = new ArrayList<>();

        dialogue.add(new Dialogue("HElllo World!"));
        dialogue.add(new Dialogue("Awwww yeahh"));
        dialogue.add(new Dialogue("Bitch you guessed it"));

        this.tinyMCEText = "<p>Hello World! Awww Yeah! Bitch you guessed it</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p><strong>Working like a champ</strong></p>";
    }


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

    public Key<Canvas> getCanvasElement() {
        return canvasElement;
    }

    public void setCanvasElement(Key<Canvas> canvasElement) {
        this.canvasElement = canvasElement;
    }

    public List<Key<Dialogue>> getDialogueList() {
        return dialogueList;
    }

    public List<Dialogue> getDialogue(){

        return dialogue;
    }

    public void setDialogueList(List<Key<Dialogue>> dialogueList) {
        this.dialogueList = dialogueList;
    }

    public String getTinyMCEText() {
        return tinyMCEText;
    }

    public void setTinyMCEText(String tinyMCEText) {
        this.tinyMCEText = tinyMCEText;
    }

    @Override
    public String toString() {
        return "\nScene{" +
                "id=" + id +
                ", index=" + index +
                ", setting='" + setting + '\'' +
                ", tinyMCEText='" + tinyMCEText +
                ", \ndialogue=" + dialogue +
                "\n}\n";
    }

    // get the Key of a Doodle
    public Key<Scene> getKey() {
        return Key.create(Scene.class, this.id);
    }
}
