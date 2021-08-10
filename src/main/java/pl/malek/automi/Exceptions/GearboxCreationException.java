package pl.malek.automi.Exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class GearboxCreationException extends Exception {
    private List<String> messages;

    public GearboxCreationException(List<String> messages) {
        this.messages = messages;
    }
}
