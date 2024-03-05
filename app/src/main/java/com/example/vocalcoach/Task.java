package com.example.vocalcoach;

import java.util.Objects;

public class Task {

    private String name;
    private String url;
    private int id;
    private int type;
    private int iconResource;
    //private int isFav;

    public Task(String name, int type, String url, int id,  int iconResource){
        this.type = type;
        this.name = name;
        this.url = url;
        this.id = id;
        this.iconResource = iconResource;
        //this.isFav = isFav;
    }

    public int getIconResource() {
        return this.iconResource;
    }

    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int isFav() {
//        return isFav;
//    }
//
//    public void setFav(int fav) {
//        isFav = fav;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (type != task.type) return false;
        if (id != task.id) return false;
        if (!Objects.equals(name, task.name)) return false;
        return Objects.equals(url, task.url);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + type;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }

}
