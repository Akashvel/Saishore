package com.example.projecttwo;

public class publish {
    String id;
    String subcode;
    String subname;
    String title;
    String description;
    String lastdate;
    String link;

    public publish(){

    }

    public publish(String id, String subcode, String subname, String title, String description, String lastdate, String link) {
        this.id = id;
        this.subcode = subcode;
        this.subname = subname;
        this.title = title;
        this.description = description;
        this.lastdate = lastdate;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public String getSubcode() {
        return subcode;
    }

    public String getSubname() {
        return subname;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLastdate() {
        return lastdate;
    }

    public String getLink() {
        return link;
    }
}
