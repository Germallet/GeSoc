package Seguridad;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class Hash {

    public byte[] bytes;

    public Hash(String datos, Salt salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec especificaciones = new PBEKeySpec(datos.toCharArray(), salt.ObtenerBytes(), 65536, 128);
        SecretKeyFactory fabricaDeHash = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        bytes = fabricaDeHash.generateSecret(especificaciones).getEncoded();
    }

    private byte[] ObtenerBytes() {
        return bytes;
    }

    public boolean EsIgualA(Hash otroHash) {
        return Arrays.equals(otroHash.ObtenerBytes(), ObtenerBytes());
    }
}

