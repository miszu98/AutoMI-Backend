package pl.malek.automi.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class RoleCreationException extends Exception {
    List<String> messages;
    public RoleCreationException(List<String> messages) {
        this.messages = messages;
    }

}
