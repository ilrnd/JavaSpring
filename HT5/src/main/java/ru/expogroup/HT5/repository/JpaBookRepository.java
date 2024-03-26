package ru.expogroup.HT5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.expogroup.HT5.entity.Book;
import ru.expogroup.HT5.entity.Reader;

public interface JpaBookRepository extends JpaRepository<Book, Long> {
    Book findByName(String name);
    Book findById(long id);
}
