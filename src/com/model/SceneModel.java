package com.model;

/**
 * Created by drodrigues on 4/8/16.
 */
public class SceneModel {

    private String id;

    private String setting;

    private String tinyMCEText;

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }


    public String getTinyMCEText() {
        return tinyMCEText;
    }

    public void setTinyMCEText(String tinyMCEText) {
        this.tinyMCEText = tinyMCEText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\nSceneModel{" +
                "id=" + id +
                ", setting='" + setting + '\'' +
                ", tinyMCEText='" + tinyMCEText + "\'" +
                "\n}\n";
    }

}
