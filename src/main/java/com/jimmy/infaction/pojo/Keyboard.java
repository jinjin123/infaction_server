package com.jimmy.infaction.pojo;

import java.util.Date;

public class Keyboard {
    private Integer id;

    private String hostid;

    private Date createTime;

    private String hit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHostid() {
        return hostid;
    }

    public void setHostid(String hostid) {
        this.hostid = hostid == null ? null : hostid.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit == null ? null : hit.trim();
    }
}