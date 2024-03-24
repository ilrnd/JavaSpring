package ru.expogroup.HT4.entity;

import lombok.Data;


@Data
public class Reader {
    private static long genid;
    private final long id;
    private final String name;

    public Reader(String name){
        id = genid++;
        this.name = name;
    }

}
