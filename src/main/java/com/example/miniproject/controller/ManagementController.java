package com.example.miniproject.controller;

import com.example.miniproject.config.AccountDetailsService;
import com.example.miniproject.model.Account;
import com.example.miniproject.model.Book;
import com.example.miniproject.service.BookService;
import com.example.miniproject.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("Management")
public class ManagementController {
    private final String UPLOAD_DIR = System.getProperty("user.dir")+"/src/main/resources/static/images";
    @Autowired
    private BookService bookService;
    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private AccountDetailsService accountService;

    @GetMapping("/createBook")
    public String createBook(Model model){
        model.addAttribute("book",new Book());
        return "admin/createBook";
    }

    @GetMapping("/viewBooks")
    public String viewBooks(Model model){
        model.addAttribute("books", bookService.listAllBooks());
        return "admin/viewBooks";
    }

    @PostMapping("/createBook")
    public String createBookSave(@RequestParam("imageBook")MultipartFile file, @ModelAttribute("Book") Book book){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        book.setImage(fileName);
        Book saveBook = bookService.saveBook(book);
        String finalFileName = saveBook.getId()+fileName;
        Path fileNameAndPath = Paths.get(UPLOAD_DIR,finalFileName);
        try {
            Files.write(fileNameAndPath,file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/Management/viewBooks";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id")Integer id){
        Book book = bookService.getBook(id);
        if(book!=null){
            Path path = Paths.get(UPLOAD_DIR+"/"+book.getId()+book.getImage());
            if(Files.exists(path)){
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            bookService.deleteBook(id);
        }

        return "redirect:/Management/viewBooks";
    }

    @GetMapping("/updateBook/{id}")
    public String updateBookView(@PathVariable("id")Integer id,Model model){
        Book book = bookService.getBook(id);
        model.addAttribute("book",book);
        return "admin/updateBook";
    }

    @PostMapping("/updateBook")
    public String updateBook(@RequestParam("imageBook")MultipartFile file, @ModelAttribute("Book")Book book){
        Book books = bookService.getBook(book.getId());
        if(file.isEmpty()){
            bookService.saveBook(book);
        }else{
            if(books!=null){
                Path path = Paths.get(UPLOAD_DIR+"/"+book.getId()+book.getImage());
                if(Files.exists(path)){
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                StringBuilder fileNames = new StringBuilder();
                book.setImage(fileName);
                Book saveBook = bookService.saveBook(book);
                String finalFileName = saveBook.getId()+fileName;
                Path fileNameAndPath = Paths.get(UPLOAD_DIR,finalFileName);
                try {
                    Files.write(fileNameAndPath,file.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return "redirect:/Management/viewBooks";

    }

    @GetMapping("/viewBook/{id}")
    public String viewBook(@PathVariable("id")Integer id,Model model){
        Book book = bookService.getBook(id);
        Double countRating = feedbackService.countAvgRating(id);
        if(book!=null){
            model.addAttribute("countRating",countRating);
            model.addAttribute("book",book);
            return "admin/viewBook";
        }else{
            return "error/error";
        }

    }

    @GetMapping("/viewUsers")
    public String viewUsers(Model model){
        List<Account> list = accountService.listAll().stream().filter(user -> user.getRole().equals("USER")).collect(Collectors.toList());
        model.addAttribute("list",list);
        return "admin/viewUsers";
    }

    @GetMapping("/viewPublishers")
    public String viewPublisher(Model model){
        List<Account> list = accountService.listAll().stream().filter(user -> user.getRole().equals("PUBLISHER")).collect(Collectors.toList());
        model.addAttribute("list",list);
        return "admin/viewPublishers";
    }

    @GetMapping("/createPublisher")
    public String createPublisher(Model model){
        model.addAttribute("account",new Account());
        return "admin/createPublisher";
    }

    @PostMapping("/createPublisher")
    public String createPublisher(Model model,@ModelAttribute("account")Account account){
        account.setRole("PUBLISHER");
        accountService.registerAccount(account);
        return "redirect:/Management/viewPublishers";
    }
}
