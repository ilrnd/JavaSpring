package ru.expogroup.HT5.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.expogroup.HT5.controllers.BookRequest;
import ru.expogroup.HT5.entity.Book;
import ru.expogroup.HT5.entity.Reader;
import ru.expogroup.HT5.repository.JpaBookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JpaBookService {
    private final JpaBookRepository repository;

//    @EventListener(ContextRefreshedEvent.class)
//    public void onCreateDB(){
//        repository.save(new Book("Война и мир"));
//        repository.save(new Book("Мастер и маргарита"));
//        repository.save(new Book("1984"));
//        repository.save(new Book("Преступление и наказание"));
//        repository.save(new Book("Анна Каренина"));
//
//    }

    public List<Book> getAllBooks(){
        return repository.findAll();
    }

    public Book findByName(String name) {
        return repository.findByName(name);
    }

    public Book createReader(BookRequest bookRequest) {
        Book book = new Book(bookRequest.getName());
        repository.save(book);
        return book;
    }

    public boolean delete(long id) {
        Book book = repository.findById(id);
        if (book != null){
            repository.delete(book);
            return true;
        } else {
            return false;
        }
    }
}
