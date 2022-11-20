package com.example.miniproject.controller;

import com.example.miniproject.config.AccountDetailsService;
import com.example.miniproject.model.Account;
import com.example.miniproject.model.AttempBook;
import com.example.miniproject.model.Book;
import com.example.miniproject.model.Feedback;
import com.example.miniproject.service.BookService;
import com.example.miniproject.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private BookService bookService;
    @Autowired
    private AccountDetailsService accountService;
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String index(Model model, @PathParam("keySearch") String keySearch) {
        List<Book> listBook;
        List<AttempBook> attempBookList = new ArrayList<>();
        if (keySearch == null) {
            listBook = bookService.listAllBooks();
            for (Book books : listBook) {
                AttempBook attempBook = new AttempBook(books.getId(), books.getTitle(), books.getPrice(),
                        books.getDescription(), books.getImage(),
                        feedbackService.countAvgRating(books.getId()) != null ? feedbackService.countAvgRating(books.getId()) : 0);
                attempBookList.add(attempBook);
            }
            model.addAttribute("books", attempBookList);
        } else {
            listBook = bookService.searchBook(keySearch);
           if(listBook.size()>0){
               for (Book books : listBook) {
                   AttempBook attempBook = new AttempBook(books.getId(), books.getTitle(), books.getPrice(),
                           books.getDescription(), books.getImage(),
                           feedbackService.countAvgRating(books.getId()) != null ? feedbackService.countAvgRating(books.getId()) : 0);
                   attempBookList.add(attempBook);
               }
               model.addAttribute("books", attempBookList);
           }else{
               model.addAttribute("keySearch", keySearch);
           }
        }
        return "index";
    }

    @GetMapping("/detailsBook/{id}")
    public String detailsBook(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("book", bookService.getBook(id));
        model.addAttribute("avgRating", feedbackService.countAvgRating(id) != null ? feedbackService.countAvgRating(id) : 0);
//        model.addAttribute("feedback",new Feedback());
        return "details";
    }

    @PostMapping("/comment")
    public String comment(@RequestParam("bookId") Integer bookId,
                          @RequestParam("userName") String username,
                          @RequestParam("rating") Double rating,
                          @RequestParam("comment") String comment) {
        Account account = accountService.getOneAccount(username);
        Feedback feedback = new Feedback(comment, rating, bookId, account.getId(), new Date());
        feedbackService.saveFeedback(feedback);
        return "redirect:/detailsBook/" + bookId;
    }


}
