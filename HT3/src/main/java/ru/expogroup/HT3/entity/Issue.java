package ru.expogroup.HT3.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Issue {
    private static  long genId;
    private final long id;
    private  final long idReader;
    private final long idBook;
    private final LocalDateTime time;

    public Issue(long idReader, long idBook){
        id = genId++;
        this.idReader = idReader;
        this.idBook = idBook;
        time = LocalDateTime.now();
    }
}
