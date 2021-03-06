package pl.malek.automi.utils;

import java.util.Optional;

/**
 * Generate template for each type of object
 * @author Michał Małek
 * @version 1.0
 */
public class GenericTest {
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
