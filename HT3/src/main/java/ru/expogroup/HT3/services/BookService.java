package ru.expogroup.HT3.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.expogroup.HT3.controllers.BookRequest;
import ru.expogroup.HT3.entity.Book;
import ru.expogroup.HT3.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class BookService {
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
}
