package ru.expogroup.HT5.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.expogroup.HT5.entity.Reader;
import ru.expogroup.HT5.services.JpaReaderService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class JpaReaderController {
    private final JpaReaderService service;

    @GetMapping("db/readers")
    public List<Reader> getReaders(){
        return service.getReaders();
    }

    @GetMapping("db/reader")
    public Reader findByName(@RequestParam String name){
        return service.findByName(name);
    }

    @PostMapping("db/reader")
    public ResponseEntity<Reader> createReader(@RequestBody ReaderRequest readerRequest){
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.createReader(readerRequest));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("db/reader")
    public ResponseEntity<Reader> deleteReaderById(@RequestParam long id){
        final boolean deleted = service.delete(id);
        return  deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
