package ru.expogroup.HT4.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.expogroup.HT4.entity.Issue;

import java.util.ArrayList;
import java.util.List;

@Repository
public class IssueRepository {

    private List<Issue> issues = new ArrayList<>();
    @Value("${application.issue.max-allowed-books:1}")
    private int MAX_ALLOWED_BOOKS;

    public void createIssue(Issue issue){
        issues.add(issue);
    }

    public Issue findById(long id){
        return issues.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean isReaderCanTakeBook(long readerId){
        return issues.stream().filter(e -> e.getIdReader() == readerId).count() < MAX_ALLOWED_BOOKS;
    }

    public List<Issue> getIssuesByReader(long readerId){
        List<Issue> issueList = issues.stream().filter(e -> e.getIdReader() == readerId).toList();
        return issueList;
    }
}
