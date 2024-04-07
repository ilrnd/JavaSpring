package ru.expogroup.HT5.controllers.jpa_controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.expogroup.HT5.controllers.base_controllers.BookRequest;
import ru.expogroup.HT5.entity.Book;
import ru.expogroup.HT5.entity.Reader;
import ru.expogroup.HT5.services.jpa_services.JpaBookService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class JpaBookController {
    private final JpaBookService service;

    @GetMapping("db/books")
    public List<Book> getBooks(){
        return service.getAllBooks();
    }

    @GetMapping("db/book")
    public Book findByName(@RequestParam String name){
        return service.findByName(name);
    }

    @PostMapping("db/book")
    public ResponseEntity<Book> createBook(@RequestBody BookRequest bookRequest){
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.createReader(bookRequest));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("db/book")
    public ResponseEntity<Reader> deleteBookById(@RequestParam long id){
        final boolean deleted = service.delete(id);
        return  deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
