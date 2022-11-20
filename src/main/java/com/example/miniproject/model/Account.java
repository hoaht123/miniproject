package com.example.miniproject.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Account")
public class Account {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "username", nullable = true, length = 45)
    private String username;
    @Basic
    @Column(name = "password", nullable = true, length = 100)
    private String password;
    @Basic
    @Column(name = "role", nullable = true, length = 45)
    private String role;
    @OneToMany(mappedBy = "accountByAccountId")
    private Collection<Feedback> feedbacksById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && Objects.equals(username, account.username) && Objects.equals(password, account.password) && Objects.equals(role, account.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role);
    }

    public Collection<Feedback> getFeedbacksById() {
        return feedbacksById;
    }

    public void setFeedbacksById(Collection<Feedback> feedbacksById) {
        this.feedbacksById = feedbacksById;
    }
}
