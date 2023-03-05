package shadow.julia.english.exception;

public class EmptyPropertyException extends RuntimeException {

    public EmptyPropertyException(String... propertyNames) {
        super(String.join(",", propertyNames) + " can't be empty");
    }
}
