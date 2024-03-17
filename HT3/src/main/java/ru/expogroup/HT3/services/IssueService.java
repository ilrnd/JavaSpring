package ru.expogroup.HT3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.expogroup.HT3.controllers.IssueRequest;
import ru.expogroup.HT3.entity.Issue;
import ru.expogroup.HT3.repository.BookRepository;
import ru.expogroup.HT3.repository.IssueRepository;
import ru.expogroup.HT3.repository.ReaderRepository;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Slf4j
public class IssueService {
    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;

    public Issue createIssue(IssueRequest request) {
        if (bookRepository.findById(request.getBookId()) == null) {
            log.info("Не удалось найти книгу с id " + request.getBookId());
            throw new NoSuchElementException("Не удалось найти книгу с id " + request.getBookId());
        }
        if (readerRepository.findById(request.getReaderId()) == null) {
            log.info("Не удалось найти читателя с id " + request.getReaderId());
            throw new NoSuchElementException("Не удалось найти читателя с id " + request.getReaderId());
        }
        Issue issue = new Issue(request.getReaderId(), request.getBookId());
        issueRepository.createIssue(issue);
        return issue;
    }
}
