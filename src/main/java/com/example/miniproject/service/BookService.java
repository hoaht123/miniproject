package com.example.miniproject.service;

import com.example.miniproject.model.Book;
import com.example.miniproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    @Qualifier("Books")
    private BookRepository repository;

    public List<Book> listAllBooks(){
        return repository.findAll();
    }

    public Book saveBook(Book book){
      repository.save(book);
     return book;
    }

    public void deleteBook(int id){
        repository.deleteById(id);
    }

    public Book getBook(Integer id){
        return repository.findById(id).get();
    }
    public List<Book> searchBook(String keySearch){
        return repository.searchLike(keySearch);
    }
}
