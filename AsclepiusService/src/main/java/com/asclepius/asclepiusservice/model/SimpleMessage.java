package com.asclepius.asclepiusservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleMessage {

    public static SimpleMessage assistantMessage(String s) {
        return new SimpleMessage("message", "assistant", "assistant", s, null);
    }

    public static SimpleMessage physicianDataMessage(String response, Physician physician) {
        return new SimpleMessage("data", "assistant", "assistant", response, physician);
    }

    private String type;
    private String role;
    private String author;
    private String content;
    private Object data;
}
