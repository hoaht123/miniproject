package com.example.miniproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Book {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "title", nullable = true, length = 45)
    private String title;
    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    private Double price;
    @Basic
    @Column(name = "description", nullable = true, length = 500)
    private String description;
    @Basic
    @Column(name = "image", nullable = true, length = 100)
    private String image;

    @OneToMany(mappedBy = "bookByBookId")
    @JsonBackReference
    private List<Feedback> feedbacksById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(title, book.title) && Objects.equals(price, book.price) && Objects.equals(description, book.description) && Objects.equals(image, book.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, description, image);
    }

    public List<Feedback> getFeedbacksById() {
        return feedbacksById;
    }

    public void setFeedbacksById(List<Feedback> feedbacksById) {
        this.feedbacksById = feedbacksById;
    }
}
