package ru.expogroup.HT3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.expogroup.HT3.controllers.IssueRequest;
import ru.expogroup.HT3.entity.Book;
import ru.expogroup.HT3.entity.Issue;
import ru.expogroup.HT3.entity.Reader;
import ru.expogroup.HT3.repository.BookRepository;
import ru.expogroup.HT3.repository.IssueRepository;
import ru.expogroup.HT3.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.List;
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

    public boolean isReaderCanTakeBook(IssueRequest request){
        if(issueRepository.isReaderCanTakeBook(request.getReaderId())){
            log.info("Читателю с readerId= {} выдана книга bookId= {}",
                    request.getReaderId(), request.getBookId());
            return true;
        } else {
            log.info("У читателя с readerId= {} слишком много книг",
                    request.getReaderId());
            return false;
        }
    }

    public Issue getById(long id){
        return issueRepository.findById(id);
    }

    public Issue returnBook(long id){
        Issue issue = issueRepository.findById(id);
        if(issue != null){
            issue.setReturned_at(LocalDateTime.now());
            log.info("Произведен возврат issueId = {}",
                    id);
        } else {
            log.info("Возврат невозможен, выдача issueId = {} не обнаружена",
                    id);
        }
        return issue;
    }

    public List<Issue> getAll() {
        return issueRepository.getAll();
    }
}
