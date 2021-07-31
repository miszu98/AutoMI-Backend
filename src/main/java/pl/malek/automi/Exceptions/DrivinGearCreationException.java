package pl.malek.automi.Exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class DrivinGearCreationException extends Exception {
    private List<String> messages;

    public DrivinGearCreationException(List<String> messages) {
        this.messages = messages;
    }
}
