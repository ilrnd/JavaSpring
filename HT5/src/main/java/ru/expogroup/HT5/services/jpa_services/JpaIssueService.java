package ru.expogroup.HT5.services.jpa_services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.expogroup.HT5.controllers.base_controllers.IssueRequest;
import ru.expogroup.HT5.entity.Issue;
import ru.expogroup.HT5.repository.jpa_repository.JpaBookRepository;
import ru.expogroup.HT5.repository.jpa_repository.JpaIssueRepository;
import ru.expogroup.HT5.repository.jpa_repository.JpaReaderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Slf4j
public class JpaIssueService {
    @Value("${application.issue.max-allowed-books:1}")
    private int MAX_ALLOWED_BOOKS;
    private final JpaBookRepository jpaBookRepository;
    private final JpaIssueRepository jpaIssueRepository;
    private final JpaReaderRepository jpaReaderRepository;

    public Issue createIssue(IssueRequest request) {
        if (jpaBookRepository.findById(request.getBookId()) == null) {
            log.info("Не удалось найти книгу с id " + request.getBookId());
            throw new NoSuchElementException("Не удалось найти книгу с id " + request.getBookId());
        }
        if (jpaReaderRepository.findById(request.getReaderId()) == null) {
            log.info("Не удалось найти читателя с id " + request.getReaderId());
            throw new NoSuchElementException("Не удалось найти читателя с id " + request.getReaderId());
        }
        Issue issue = new Issue(request.getReaderId(), request.getBookId());
        jpaIssueRepository.save(issue);
        return issue;
    }

    public boolean isReaderCanTakeBook(IssueRequest request) {
        if (isReaderCanTakeBook(request.getReaderId())) {
            log.info("Читателю с readerId= {} выдана книга bookId= {}",
                    request.getReaderId(), request.getBookId());
            return true;
        } else {
            log.info("У читателя с readerId= {} слишком много книг",
                    request.getReaderId());
            return false;
        }
    }

    public Issue getById(long id) {
        return jpaIssueRepository.findById(id);
    }

    public Issue returnBook(long id) {
        Issue issue = jpaIssueRepository.findById(id);
        if (issue != null) {
            issue.setReturned_at(LocalDateTime.now());
            log.info("Произведен возврат issueId = {}",
                    id);
            jpaIssueRepository.save(issue);
        } else {
            log.info("Возврат невозможен, выдача issueId = {} не обнаружена",
                    id);
        }
        return issue;
    }

    public List<Issue> getAll() {
        return jpaIssueRepository.findAll();
    }

    private boolean isReaderCanTakeBook(long readerId) {
        return getAll().stream()
                .filter(e -> e.getIdReader() == readerId)
                .count() < MAX_ALLOWED_BOOKS;
    }
}
