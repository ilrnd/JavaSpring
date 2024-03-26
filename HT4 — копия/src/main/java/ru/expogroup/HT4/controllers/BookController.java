package ru.expogroup.HT4.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.expogroup.HT4.entity.Book;
import ru.expogroup.HT4.services.BookService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("book/{id}")
    public ResponseEntity<Book> getById(@PathVariable long id){
        final Book book = bookService.getById(id);
        log.info("Поступил запрос данных по книге: bookId = {}", id);
        return  book != null
                ? new ResponseEntity<>(book, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("book/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable long id){
        final boolean deleted = bookService.delete(id);
        return  deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("book")
    public ResponseEntity<Book> createBook(@RequestBody BookRequest bookRequest){
        log.info("Поступил запрос на создание книги: name = {}",
                bookRequest.getName());
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(bookService.createBook(bookRequest));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("books")
    public String lookAllBooks(Model model){
        List<Book> allBooks = bookService.getAllBooks();
        log.info("Поступил запрос на выдачу списка всех книг");
        model.addAttribute("books", allBooks);
        System.out.println(allBooks);
        return "books";
    }
}