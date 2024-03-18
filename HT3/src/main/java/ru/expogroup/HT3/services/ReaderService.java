package ru.expogroup.HT3.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.expogroup.HT3.controllers.BookRequest;
import ru.expogroup.HT3.controllers.ReaderController;
import ru.expogroup.HT3.controllers.ReaderRequest;
import ru.expogroup.HT3.entity.Book;
import ru.expogroup.HT3.entity.Reader;
import ru.expogroup.HT3.repository.BookRepository;
import ru.expogroup.HT3.repository.ReaderRepository;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    public Reader getById(long id){
        return readerRepository.findById(id);
    }

    public boolean delete(long id){
        Reader deletingReader = getById(id);
        if (deletingReader != null){
            readerRepository.delete(deletingReader);
            return true;
        } else {
            return false;
        }
    }

    public Reader createReader(ReaderRequest readerRequest){
        Reader reader = new Reader(readerRequest.getName());
        readerRepository.create(reader);
        return reader;
    }
}
