package ru.expogroup.HT5.controllers.base_controllers;

import lombok.Data;

@Data
public class IssueRequest {
    private long readerId;
    private long bookId;
}
