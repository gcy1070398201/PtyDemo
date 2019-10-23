package com.example.pty.dto;

import java.util.ArrayList;
import java.util.List;

public class ProfileQuestionsDto {

    private List<PublishDto> publishDtos;
    private boolean showFirstPage;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private Integer totalPage;
    private List<Integer>pages=new ArrayList<>();

    public List<PublishDto> getPublishDtos() {
        return this.publishDtos;
    }

    public void setPublishDtos(final List<PublishDto> publishDtos) {
        this.publishDtos = publishDtos;
    }

    public boolean isShowFirstPage() {
        return this.showFirstPage;
    }

    public void setShowFirstPage(final boolean showFirstPage) {
        this.showFirstPage = showFirstPage;
    }

    public boolean isShowPrevious() {
        return this.showPrevious;
    }

    public void setShowPrevious(final boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean isShowNext() {
        return this.showNext;
    }

    public void setShowNext(final boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowEndPage() {
        return this.showEndPage;
    }

    public void setShowEndPage(final boolean showEndPage) {
        this.showEndPage = showEndPage;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(final Integer page) {
        this.page = page;
    }

    public Integer getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(final Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<Integer> getPages() {
        return this.pages;
    }

    public void setPages(final List<Integer> pages) {
        this.pages = pages;
    }

    public void setPageInfo(Integer totalPage, Integer page, Integer size) {
        this.totalPage=totalPage;
        this.page=page;
        pages.add(page);
        for (int i = 1; i <=3; i++) {
            if (page-i>0){
                pages.add(0,page-i);
            }
            if (page+i<=this.totalPage){

                pages.add(page+i);
            }
        }
        //判断是否显示上一页
        if (page==1){
            showPrevious=false;
        }else{
            showPrevious=true;
        }
        //判断是否显示下一页
        if (page==totalPage){
            showNext=false;
        }else{
            showNext=true;
        }
        //判断是否显示第一页
        if (pages.contains(1)){
            showFirstPage=false;
        }else{
            showFirstPage=true;
        }
        //判断是否显示最后一页
        if (pages.contains(totalPage)){
            showEndPage=false;
        }else{
            showEndPage=true;
        }

    }
}
