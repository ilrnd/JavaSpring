package ru.expogroup.HT4.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.expogroup.HT4.controllers.ReaderRequest;
import ru.expogroup.HT4.entity.Issue;
import ru.expogroup.HT4.entity.Reader;
import ru.expogroup.HT4.repository.IssueRepository;
import ru.expogroup.HT4.repository.ReaderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;
    public Reader getById(long id){
        return readerRepository.findById(id);
    }

    public boolean delete(long id){
        Reader deletingReader = getById(id);
        if (deletingReader != null){
            readerRepository.delete(deletingReader);
            return true;
        } else {
            return false;
        }
    }

    public Reader createReader(ReaderRequest readerRequest){
        Reader reader = new Reader(readerRequest.getName());
        readerRepository.create(reader);
        return reader;
    }

    public List<Issue> getIssues(long id){
        List<Issue> issueList = issueRepository.getIssuesByReader(id);
        return issueList;
    }


}
