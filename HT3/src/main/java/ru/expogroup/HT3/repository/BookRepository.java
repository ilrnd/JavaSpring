package ru.expogroup.HT3.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.expogroup.HT3.entity.Book;
import ru.expogroup.HT3.entity.Issue;

import java.util.ArrayList;
import java.util.List;


@Repository
public class BookRepository {

    private List<Book> list = new ArrayList<>();

    public BookRepository(){
        list.add(new Book("Война и мир"));
        list.add(new Book("Мастери и Маргарита"));
        list.add(new Book("Приключения Буратино"));
        list.add(new Book("Маленький принц"));
    }


    public Book findById(long id){
        return list.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void delete(Book book){
        list.remove(book);
    }

    public void create(Book book){
        list.add(book);
    }

    public List<Book> getBooks(){
        return list;
    }
}
