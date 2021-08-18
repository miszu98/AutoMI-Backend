package pl.malek.automi.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class FuelTypeCreationException extends Exception {
    private List<String> messages;

    public FuelTypeCreationException(List<String> messages) {
        this.messages = messages;
    }
}
