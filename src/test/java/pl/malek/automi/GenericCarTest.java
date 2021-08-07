package pl.malek.automi;

import java.util.Optional;

public class GenericCarTest {
    public static <T> T tryToGetEntityObject(Optional<T> optionalObject) {
        T objectEntity;
        var isExist = optionalObject.isPresent();
        if (isExist) {
            objectEntity = optionalObject.get();
        } else {
            objectEntity = (T) new Object();
        }
        return objectEntity;
    }
}
