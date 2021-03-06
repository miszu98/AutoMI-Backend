package pl.malek.automi.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ColorCreationException extends Exception {
    private List<String> messages;

    public ColorCreationException(List<String> messages) {
        this.messages = messages;
    }
}
