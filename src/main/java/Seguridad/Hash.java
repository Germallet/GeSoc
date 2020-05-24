package Seguridad;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.GeneralSecurityException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class Hash {

    public byte[] bytes;

    public Hash(String datos, Salt salt) {

        KeySpec especificaciones = new PBEKeySpec(datos.toCharArray(), salt.obtenerBytes(), 65536, 128);

        try {
            SecretKeyFactory fabricaDeHash = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            bytes = fabricaDeHash.generateSecret(especificaciones).getEncoded();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] obtenerBytes() {
        return bytes;
    }

    public boolean esIgualA(Hash otroHash) {
        return Arrays.equals(otroHash.obtenerBytes(), obtenerBytes());
    }
}
