package ru.expogroup.HT5.controllers.jpa_controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.expogroup.HT5.controllers.base_controllers.IssueRequest;
import ru.expogroup.HT5.entity.Issue;
import ru.expogroup.HT5.services.jpa_services.JpaIssueService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JpaIssueController {
    @Autowired
    private JpaIssueService service;

    @PostMapping("db/issue")
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest issueRequest){
        log.info("Поступил запрос на выдачу: readerId= {}, bookId = {}",
                issueRequest.getReaderId(), issueRequest.getBookId());
        try {
            if(service.isReaderCanTakeBook(issueRequest)) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(service.createIssue(issueRequest));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("db/issue/{id}")
    public ResponseEntity<Issue> returnBook(@PathVariable long id){
        log.info("Поступил запрос на возврат: issueId= {},", id);
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.returnBook(id));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }

    }

    /**
     * Метод запроса на возврат книги
     * @param id - id возврата (issue)
     * @return - ответ на запрос
     */
    @GetMapping("db/issue/{id}")
    public ResponseEntity<Issue> getById(@PathVariable long id){
        final Issue issue = service.getById(id);
        log.info("Поступил запрос данных по выдаче: issueId = {}", id);
        return  issue != null
                ? new ResponseEntity<>(issue, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("db/issues")
    public List<Issue> getAll(){
        return service.getAll();
    }
}
