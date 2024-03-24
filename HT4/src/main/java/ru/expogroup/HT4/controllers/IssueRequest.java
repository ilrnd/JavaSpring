package ru.expogroup.HT4.controllers;

import lombok.Data;

@Data
public class IssueRequest {
    private long readerId;
    private long bookId;
}
