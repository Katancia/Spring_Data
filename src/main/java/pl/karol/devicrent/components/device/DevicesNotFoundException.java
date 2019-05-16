package pl.karol.devicrent.components.device;

public class DevicesNotFoundException extends RuntimeException {
    public DevicesNotFoundException(String message) {
        super(message);
    }
}
