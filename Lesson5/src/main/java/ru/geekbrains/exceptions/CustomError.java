package ru.geekbrains.exceptions;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CustomError {
    private Date date;
    private List<String> messages;

    public CustomError(List<String> messages) {
        this.messages = messages;
        this.date = new Date();
    }

    public CustomError(String message){
        this.messages = List.of(message);
    }

    public CustomError(String... messages) {
        this(Arrays.asList(messages));
    }
}
