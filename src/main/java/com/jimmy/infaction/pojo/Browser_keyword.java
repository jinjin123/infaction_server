package com.jimmy.infaction.pojo;

import java.util.Date;

public class Browser_keyword {
    private Integer id;

    private String keyword;

    private String brwtype;

    private String hostid;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getBrwtype() {
        return brwtype;
    }

    public void setBrwtype(String brwtype) {
        this.brwtype = brwtype == null ? null : brwtype.trim();
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
}