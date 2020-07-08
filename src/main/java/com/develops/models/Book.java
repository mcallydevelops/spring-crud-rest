package com.develops.models;

import javax.persistence.*;


@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String isn;
    private String title;
    private String publisher;
    private String publishedDate;
//    private String[] author;

    public Book() {
    }

    public Book(String isn, String title, String publisher, String publishedDate) {
        this.isn = isn;
        this.title = title;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
//        this.author = author;
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

//    public String[] getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String[] author) {
//        this.author = author;
//    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}