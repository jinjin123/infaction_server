package com.jimmy.infaction.pojo;

import java.util.Date;

public class Browser_download {
    private Integer id;

    private String url;

    private String hostid;

    private String path;

    private String brwtype;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getHostid() {
        return hostid;
    }

    public void setHostid(String hostid) {
        this.hostid = hostid == null ? null : hostid.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getBrwtype() {
        return brwtype;
    }

    public void setBrwtype(String brwtype) {
        this.brwtype = brwtype == null ? null : brwtype.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}