package com.example.pty.dto;

public class CommentDto {
    private Long parentId;
    private String content;
    private Integer type;

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(final Long parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(final Integer type) {
        this.type = type;
    }
}
