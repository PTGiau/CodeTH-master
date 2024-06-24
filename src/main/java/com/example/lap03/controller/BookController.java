package com.example.lap03.controller;


import com.example.lap03.services.BookService;
import com.example.lap03.entity.Book;
import com.example.lap03.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllBooks(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/add")
    public  String addBookFrom(Model model){
        model.addAttribute("book",new Book());
        model.addAttribute("category", categoryService.getAllCategories());
        return "book/add";
    }
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book")Book book, BindingResult result, Model model){
        if(result.hasErrors())
            return "book/add";
        bookService.addBook(book);
        return "redirect:/books";
    }
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") long id, Model model) {
        Book editBook = bookService.getBookById(id);
        if (editBook != null) {
            model.addAttribute("book", bookService.getBookById(id));
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }else{
            return "not-found";
        }
    }
    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @Valid Book updateBook, BindingResult result){
        if(bookService.getBookById(updateBook.getId())==null)
            return "not-found";
            if(result.hasErrors()){
                updateBook.setId(id);
                return "book/edit";
            }
        bookService.updateBook(updateBook);
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
