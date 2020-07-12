package com.example.projecttwo;

public class post {
    String id;
    String about;
    String des;

    public post(){

    }

    public post(String id, String about, String des) {
        this.id = id;
        this.about = about;
        this.des = des;
    }

    public String getId() {
        return id;
    }

    public String getAbout() {
        return about;
    }

    public String getDes() {
        return des;
    }
}
