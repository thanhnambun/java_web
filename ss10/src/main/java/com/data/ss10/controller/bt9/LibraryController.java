package com.data.ss10.controller.bt9;

import com.data.ss10.model.Book;
import com.data.ss10.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/library")
public class LibraryController {

    private final CloudinaryService cloudinaryService;
    private final List<Book> books = new ArrayList<>();

    @Autowired
    public LibraryController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "/bt9/bookForm";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book, Model model) {
        try {
            String fileUrl = cloudinaryService.uploadFile(book.getFile());
            Book savedBook = new Book(book.getTitle(), book.getAuthor(), book.getDescription(), null);
            books.add(savedBook);

            model.addAttribute("message", "Thêm sách thành công!");
            model.addAttribute("fileUrl", fileUrl);
            return "/bt9/success";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi upload file: " + e.getMessage());
            return "/bt9/bookForm";
        }
    }

    @GetMapping("/list")
    public String showBookList(Model model) {
        model.addAttribute("books", books);
        return "/bt9/bookList";
    }
}