package Seguridad.ValidadorDeContrasenia;

public class PasswordException extends RuntimeException {
    public PasswordException(String message) {
        super(message);
    }
}
