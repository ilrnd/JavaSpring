package ru.expogroup.HT5.repository.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.expogroup.HT5.entity.Issue;

public interface JpaIssueRepository extends JpaRepository<Issue, Long> {
    Issue findById(long id);
}
