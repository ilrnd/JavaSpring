package ru.expogroup.HT3.entity;

import lombok.Data;

@Data
public class Book {
    private static long genid;
    private final long id;
    private final String name;

    public Book(String name){
        id = genid++;
        this.name = name;
    }
}
