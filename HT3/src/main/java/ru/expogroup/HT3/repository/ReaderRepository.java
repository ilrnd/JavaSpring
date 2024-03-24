package ru.expogroup.HT3.repository;

import org.springframework.stereotype.Repository;
import ru.expogroup.HT3.entity.Book;
import ru.expogroup.HT3.entity.Issue;
import ru.expogroup.HT3.entity.Reader;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReaderRepository {
    private List<Reader> list = new ArrayList<>();

    public ReaderRepository(){
        list.add(new Reader("Илья"));
        list.add(new Reader("Иван"));
        list.add(new Reader("Петр"));
    }

    public Reader findById(long id){
        return list.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public void delete(Reader reader){
        list.remove(reader);
    }

    public void create(Reader reader){
        list.add(reader);
    }

    public List<Reader> getAll() {
        return list;
    }
}
