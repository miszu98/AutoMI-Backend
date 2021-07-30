package pl.malek.automi.Exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ModelCreationException extends Exception {
    private List<String> messages;

    public ModelCreationException(List<String> messages) {
        this.messages = messages;
    }
}
