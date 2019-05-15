package pl.karol.devicrent.app;

public class InvalidOptionException extends RuntimeException {
    public InvalidOptionException() {
        super("Option doesn't exist");
    }
}
