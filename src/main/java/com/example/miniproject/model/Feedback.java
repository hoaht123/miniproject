package com.example.miniproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@NoArgsConstructor
public class Feedback {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "comment", nullable = true, length = 100)
    private String comment;
    @Basic
    @Column(name = "rating", nullable = true, precision = 0)
    private Double rating;
    @Basic
    @Column(name = "book_id", nullable = true)
    private Integer bookId;
    @Basic
    @Column(name = "account_id", nullable = true)
    private Integer accountId;
    @Basic
    @Column(name = "date_comment", nullable = true)
    private Date dateComment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", referencedColumnName = "id",insertable = false, updatable = false)
    @JsonBackReference
    private Book bookByBookId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "id",insertable = false, updatable = false)
    private Account accountByAccountId;

    public Feedback(String comment, Double rating, Integer bookId, Integer accountId, Date dateComment) {
        this.comment = comment;
        this.rating = rating;
        this.bookId = bookId;
        this.accountId = accountId;
        this.dateComment = dateComment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }


    public Book getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(Book bookByBookId) {
        this.bookByBookId = bookByBookId;
    }

    public Account getAccountByAccountId() {
        return accountByAccountId;
    }

    public void setAccountByAccountId(Account accountByAccountId) {
        this.accountByAccountId = accountByAccountId;
    }
}
