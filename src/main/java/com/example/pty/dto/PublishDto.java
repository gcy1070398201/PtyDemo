package com.example.pty.dto;
import com.example.pty.mode.User;

public class PublishDto {
    private Long id;
    private String title;
    private String describeText;
    private String label;
    private Long creatId;//创建人的id
    private Long gmtCreate;
    private Long gmtModified;
    private Integer replyCount;//回复次数
    private Integer viewCount;//游览次数
    private User user;//User 信息

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatId() {
        return creatId;
    }

    public void setCreatId(Long creatId) {
        this.creatId = creatId;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescribeText() {
        return this.describeText;
    }

    public void setDescribeText(final String describeText) {
        this.describeText = describeText;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }



    public void setGmtCreate(final long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }


    public Integer getReplyCount() {
        return this.replyCount;
    }

    public void setReplyCount(final Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getViewCount() {
        return this.viewCount;
    }

    public void setViewCount(final Integer viewCount) {
        this.viewCount = viewCount;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
