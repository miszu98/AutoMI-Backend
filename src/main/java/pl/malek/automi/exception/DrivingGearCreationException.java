package pl.malek.automi.exception;

import lombok.Getter;
import java.util.List;

@Getter
public class DrivingGearCreationException extends Exception {
    private List<String> messages;

    public DrivingGearCreationException(List<String> messages) {
        this.messages = messages;
    }
}
