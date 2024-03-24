package ru.expogroup.HT4.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Issue {
    private static  long genId;
    private final long id;
    private  final long idReader;
    private final long idBook;
    private final LocalDateTime issued_at;
    private LocalDateTime returned_at;

    public Issue(long idReader, long idBook){
        id = genId++;
        this.idReader = idReader;
        this.idBook = idBook;
        issued_at = LocalDateTime.now();
        returned_at = null;
    }
}
