package com.develops.controllers;

public class BookRequest {

    private String isn;
    private String title;
    private String publisher;
    private String publishedDate;

    public BookRequest() {
    }

    public BookRequest(String isn, String title, String publisher, String publishedDate) {
        this.isn = isn;
        this.title = title;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
    }

    public String getIsn() {
        return isn;
    }

    public void setIsn(String isn) {
        this.isn = isn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

}

