package com.example.miniproject.controller;

import com.example.miniproject.config.AccountDetailsService;
import com.example.miniproject.model.Account;
import com.example.miniproject.model.Book;
import com.example.miniproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class APIController {
    @Autowired
    private AccountDetailsService accountService;

    @Autowired
    private BookService bookService;

    @GetMapping("/api/getAccount")
    public ResponseEntity<?> getAccount(String accountName){
        Account account = accountService.getOneAccount(accountName);
        boolean accountExist = false;
        if(account!=null){
            accountExist = true;
        }else {
            accountExist = false;
        }
        return ResponseEntity.ok(accountExist);
    }

    @PostMapping(value = "/registerAccount",consumes={"application/json"})
    @ResponseBody
    public ResponseEntity<?> registerAccount(@RequestBody Account account){
        accountService.registerAccount(account);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/loadSearch")
    public ResponseEntity<?> loadSearch(String keySearch){
        List<Book> listBook = bookService.searchBook(keySearch);
        if(listBook.size()!=0){
            return ResponseEntity.ok(listBook);
        }else{
            return ResponseEntity.ok("null");
        }

    }

}
