package ru.expogroup.HT4.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.expogroup.HT4.controllers.BookRequest;
import ru.expogroup.HT4.entity.Book;
import ru.expogroup.HT4.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    @Autowired
    private final BookRepository bookRepository;
    public Book getById(long id){
        return bookRepository.findById(id);
    }

    public boolean delete(long id){
        Book deletingBook = getById(id);
        if (deletingBook != null){
            bookRepository.delete(deletingBook);
            return true;
        } else {
            return false;
        }
    }

    public Book createBook(BookRequest bookRequest){
        Book book = new Book(bookRequest.getName());
        bookRepository.create(book);
        return book;
    }

    public List<Book> getAllBooks(){
        return bookRepository.getBooks();
    }
}
