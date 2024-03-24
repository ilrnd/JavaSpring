package ru.expogroup.HT3.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.expogroup.HT3.entity.Issue;
import ru.expogroup.HT3.entity.Reader;
import ru.expogroup.HT3.services.IssueService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IssueController {
    @Autowired
    private IssueService issueService;

    @PostMapping("issue")
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest issueRequest){
        log.info("Поступил запрос на выдачу: readerId= {}, bookId = {}",
                issueRequest.getReaderId(), issueRequest.getBookId());
        try {
            if(issueService.isReaderCanTakeBook(issueRequest)) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(issueService.createIssue(issueRequest));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("issue/{id}")
    public ResponseEntity<Issue> returnBook(@PathVariable long id){
        log.info("Поступил запрос на возврат: issueId= {},", id);
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(issueService.returnBook(id));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }

    }

    /**
     * Метод запроса на возврат книги
     * @param id - id возврата (issue)
     * @return - ответ на запрос
     */
    @GetMapping("issue/{id}")
    public ResponseEntity<Issue> getById(@PathVariable long id){
        final Issue issue = issueService.getById(id);
        log.info("Поступил запрос данных по выдаче: issueId = {}", id);
        return  issue != null
                ? new ResponseEntity<>(issue, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("ui/issues")
    public String viewAllIssues(Model model){
        List<Issue> issues = issueService.getAll();
        model.addAttribute("issues", issues);
        return "issues";
    }

}
