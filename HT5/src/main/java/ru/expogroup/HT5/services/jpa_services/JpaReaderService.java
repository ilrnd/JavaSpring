package ru.expogroup.HT5.services.jpa_services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.expogroup.HT5.controllers.base_controllers.ReaderRequest;
import ru.expogroup.HT5.entity.Reader;
import ru.expogroup.HT5.repository.jpa_repository.JpaReaderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JpaReaderService {
    private final JpaReaderRepository repository;

//    @EventListener(ContextRefreshedEvent.class)
//    public void onCreateDB(){
//        repository.save(new Reader("Илья"));
//        repository.save(new Reader("Петр"));
//        repository.save(new Reader("Василий"));
//        repository.save(new Reader("Александр"));
//        repository.save(new Reader("Иннокентий"));
//
//    }

    public List<Reader> getReaders(){
        return repository.findAll();
    }

    public Reader findByName(String name) {
        return repository.findByName(name);
    }

    public Reader findById(long id) {
        return repository.findById(id);
    }

    public Reader createReader(ReaderRequest readerRequest) {
        Reader reader = new Reader(readerRequest.getName());
        repository.save(reader);
        return reader;
    }

    public boolean delete(long id){
        Reader deletingReader = findById(id);
        if (deletingReader != null){
            repository.delete(deletingReader);
            return true;
        } else {
            return false;
        }
    }
}
