package com.data.creation;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by drodrigues on 3/30/16.
 */
@Entity
public class Dialogue {


    @Id
    private Long id;

    private String dialogue;

    private int index;

    public Dialogue(){
        this(null);
    }

    public Dialogue(String dialogue){
        this(dialogue, 0);
    }

    public Dialogue(String dialogue, int index) {
        this.dialogue = dialogue;
        this.index = index;
    }

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

   public Key<Dialogue> getKey(){
        return Key.create(Dialogue.class, id);
    }

    @Override
    public String toString() {
        return "Dialogue{" +
                "id=" + id +
                ", dialogue='" + dialogue + '\'' +
                ", index=" + index +
                "}\n";
    }
}
