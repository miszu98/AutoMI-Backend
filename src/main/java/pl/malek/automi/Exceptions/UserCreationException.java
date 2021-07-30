package pl.malek.automi.Exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class UserCreationException extends Exception {
    private List<String> messages;

    public UserCreationException(List<String> messages) {
        this.messages = messages;
    }
}
