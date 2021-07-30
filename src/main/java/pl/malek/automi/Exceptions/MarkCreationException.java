package pl.malek.automi.Exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class MarkCreationException extends Exception {
    private List<String> messages;

    public MarkCreationException(List<String> messages) {
        this.messages = messages;
    }
}
