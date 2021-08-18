package pl.malek.automi.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class CarCreationException extends Exception {
    private List<String> messages;

    public CarCreationException(List<String> messages) {
        this.messages = messages;
    }
}
