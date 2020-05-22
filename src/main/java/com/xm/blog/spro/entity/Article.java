package com.xm.blog.spro.entity;

public class Article {
    private Integer id;

    private Long articleid;

    private String author;

    private String originalauthor;

    private String articletitle;

    private String articletags;

    private String articletype;

    private String articlecategories;

    private String publishdate;

    private String updatedate;

    private String articleurl;

    private String articletabloid;

    private Integer likes;

    private Long lastarticleid;

    private Long nextarticleid;

    private String articlecontent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getArticleid() {
        return articleid;
    }

    public void setArticleid(Long articleid) {
        this.articleid = articleid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOriginalauthor() {
        return originalauthor;
    }

    public void setOriginalauthor(String originalauthor) {
        this.originalauthor = originalauthor;
    }

    public String getArticletitle() {
        return articletitle;
    }

    public void setArticletitle(String articletitle) {
        this.articletitle = articletitle;
    }

    public String getArticletags() {
        return articletags;
    }

    public void setArticletags(String articletags) {
        this.articletags = articletags;
    }

    public String getArticletype() {
        return articletype;
    }

    public void setArticletype(String articletype) {
        this.articletype = articletype;
    }

    public String getArticlecategories() {
        return articlecategories;
    }

    public void setArticlecategories(String articlecategories) {
        this.articlecategories = articlecategories;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public String getArticleurl() {
        return articleurl;
    }

    public void setArticleurl(String articleurl) {
        this.articleurl = articleurl;
    }

    public String getArticletabloid() {
        return articletabloid;
    }

    public void setArticletabloid(String articletabloid) {
        this.articletabloid = articletabloid;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Long getLastarticleid() {
        return lastarticleid;
    }

    public void setLastarticleid(Long lastarticleid) {
        this.lastarticleid = lastarticleid;
    }

    public Long getNextarticleid() {
        return nextarticleid;
    }

    public void setNextarticleid(Long nextarticleid) {
        this.nextarticleid = nextarticleid;
    }

    public String getArticlecontent() {
        return articlecontent;
    }

    public void setArticlecontent(String articlecontent) {
        this.articlecontent = articlecontent;
    }
}