package com.example.miniproject.service;

import com.example.miniproject.model.Feedback;
import com.example.miniproject.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    @Autowired
    @Qualifier("feedback")
    private FeedbackRepository repository;

    public void saveFeedback(Feedback feedback){
        repository.save(feedback);
    }

    public Double countAvgRating(Integer bookId){
        return repository.avgRating(bookId);
    }
}
