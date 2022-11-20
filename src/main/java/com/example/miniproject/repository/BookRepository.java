package com.example.miniproject.repository;

import com.example.miniproject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository("Books")
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.title LIKE %:keyword%")
    List<Book> searchLike(@RequestParam("keyword")String keyword);
}