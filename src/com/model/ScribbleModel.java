package com.model;


import java.util.List;

/**
 * Created by drodrigues on 4/8/16.
 */
public class ScribbleModel {

    private Long id;

    private String title;

    private String description;

    private List<PageModel> pages;

    public ScribbleModel(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PageModel> getPages() {
        return pages;
    }

    public void setPages(List<PageModel> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "ScribbleModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", pages=\n" + pages +
                "\n}";
    }
}
