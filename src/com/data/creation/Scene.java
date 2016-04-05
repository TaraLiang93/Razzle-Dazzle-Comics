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

    private Key<Canvas> canvasElement;

    private List<Key<Dialogue>> dialogueList;

    static int count = 0;

    public Scene(String setting){
        this.setting = setting;
        this.index = count++;
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
        List<Dialogue> newList = new ArrayList<>();

        newList.add(new Dialogue("HElllo World!"));
        newList.add(new Dialogue("Awwww yeahh"));
        newList.add(new Dialogue("Bitch you guessed it"));

        return newList;
    }

    public void setDialogueList(List<Key<Dialogue>> dialogueList) {
        this.dialogueList = dialogueList;
    }
}
