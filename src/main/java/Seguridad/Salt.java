package Seguridad;

import org.hibernate.annotations.Type;

import javax.persistence.Lob;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.security.SecureRandom;

@Embeddable
public class Salt {
    //@Type(type = "org.hibernate.type.BlobType")
    @Lob
    @Column(name = "contrasenia_salt")
    private byte[] bytes;

    public Salt() {
        bytes = new byte[20];
        new SecureRandom().nextBytes(bytes);
    }

    public byte[] obtenerBytes() {
        return bytes;
    }
}
