package ru.expogroup.HT3.repository;

import org.springframework.stereotype.Repository;
import ru.expogroup.HT3.entity.Issue;

import java.util.ArrayList;
import java.util.List;

@Repository
public class IssueRepository {

    private List<Issue> list = new ArrayList<>();

    public void createIssue(Issue issue){
        list.add(issue);
    }

    public Issue findById(long id){
        return list.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
