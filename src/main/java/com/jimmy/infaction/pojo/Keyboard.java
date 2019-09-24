package com.jimmy.infaction.pojo;

public class Keyboard {
    private Integer id;

    private String hit;

    private Integer username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit == null ? null : hit.trim();
    }

    public Integer getUsername() {
        return username;
    }

    public void setUsername(Integer username) {
        this.username = username;
    }
}