package com.asclepius.asclepiusservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleMessage {

    public static SimpleMessage assistantMessage(String s) {
        return new SimpleMessage("assistant", "assistant", s);
    }

    private String role;
    private String author;
    private String content;
}
