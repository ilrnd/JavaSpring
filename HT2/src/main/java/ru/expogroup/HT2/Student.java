package ru.expogroup.HT2;

import lombok.Data;

@Data
public class Student {
    private static long curId = 0;
    private final long id;
    private final String studentName;
    private final String groupName;

    public Student(String studentName, String groupName) {
        this.id = curId++;
        this.studentName = studentName;
        this.groupName = groupName;
    }


}
