package ru.expogroup.HT3.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
     return true;
    }
}
