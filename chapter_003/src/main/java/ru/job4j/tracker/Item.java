package ru.job4j.tracker;

import java.sql.Array;

public class Item {
    private String id;
    private String name;
    private String desk;
    private long create;
    private String[] comments;

    public Item(String id, String name, String desk, long create, String[] comments) {
        this.id = id;
        this.name = name;
        this.desk = desk;
        this.create = create;
        this.comments = comments;
    }

    public Item() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }

    public long getCreate() {
        return create;
    }

    public void setCreate(long create) {
        this.create = create;
    }

    public String[] getComments() {
        return comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }
}
