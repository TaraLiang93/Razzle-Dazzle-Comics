package com.model;

import java.util.List;

/**
 * Created by drodrigues on 4/8/16.
 */
public class PageModel {

    private String id;

    private List<SceneModel> scenes;

    public List<SceneModel> getScenes() {
        return scenes;
    }

    public void setScenes(List<SceneModel> scenes) {
        this.scenes = scenes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PageModel{" +
                "id=" + id +
                ", \nscenes=" + scenes +
                "\n}";
    }
}
