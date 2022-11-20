package com.example.miniproject.repository;

import com.example.miniproject.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("feedback")
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.bookId = :bookId")
    Double avgRating(@Param("bookId")Integer bookId);
}