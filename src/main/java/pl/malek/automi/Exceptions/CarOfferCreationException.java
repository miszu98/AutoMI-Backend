package pl.malek.automi.Exceptions;

import lombok.Getter;
import java.util.List;

@Getter
public class CarOfferCreationException extends Exception {
    private List<String> messages;

    public CarOfferCreationException(List<String> messages) {
        this.messages = messages;
    }
}
