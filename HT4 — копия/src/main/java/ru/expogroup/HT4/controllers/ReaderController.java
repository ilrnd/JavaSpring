package ru.expogroup.HT4.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.expogroup.HT4.entity.Issue;
import ru.expogroup.HT4.entity.Reader;
import ru.expogroup.HT4.services.ReaderService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @GetMapping("reader/{id}")
    public ResponseEntity<Reader> getById(@PathVariable long id){
        final Reader reader = readerService.getById(id);
        log.info("Поступил запрос данных по читатель: readerId = {}", id);
        return  reader != null
                ? new ResponseEntity<>(reader, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("reader/{id}/issue")
    public ResponseEntity<List<Issue>> getIssuesByReaderId(@PathVariable long id){
        final List<Issue> issueList = readerService.getIssues(id);
        return issueList !=null
                ? new ResponseEntity<>(issueList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("reader/{id}")
    public ResponseEntity<Reader> deleteReaderById(@PathVariable long id){
        final boolean deleted = readerService.delete(id);
        return  deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("reader")
    public ResponseEntity<Reader> createReader(@RequestBody ReaderRequest readerRequest){
        log.info("Поступил запрос на создание читателя: name = {}",
                readerRequest.getName());
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(readerService.createReader(readerRequest));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
}
