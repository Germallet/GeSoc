package Seguridad;

import java.security.SecureRandom;

public class Salt {

    private byte[] bytes;

    public Salt() {
        bytes = new byte[20];
        new SecureRandom().nextBytes(bytes);
    }

    public byte[] obtenerBytes() {
        return bytes;
    }
}
