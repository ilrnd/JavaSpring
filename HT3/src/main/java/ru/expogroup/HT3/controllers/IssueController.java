package ru.expogroup.HT3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.expogroup.HT3.entity.Issue;
import ru.expogroup.HT3.services.IssueService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("issue")
@RequiredArgsConstructor
public class IssueController {
    private IssueService service;

    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest issueRequest){
        log.info("Поступил запрос на выдачу: readerId= {}, bookId = {}",
                issueRequest.getReaderId(), issueRequest.getBookId());
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.createIssue(issueRequest));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
}
