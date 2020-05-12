package Seguridad;

import com.google.common.base.Preconditions;

import java.security.*;
import java.security.spec.*;
import java.util.regex.*;

public class Contrasenia {

    private Hash hash;
    private Salt salt;

    public Contrasenia(String contrasenia) throws InvalidKeySpecException, NoSuchAlgorithmException {
        validarPrecondiciones(contrasenia);
        salt = new Salt();
        hash = new Hash(contrasenia, salt);
    }

    private void validarPrecondiciones(String contrasenia) {
        Preconditions.checkArgument(contrasenia.length() >= 8, new IllegalArgumentException("Contraseña demasiado corta"));
        Preconditions.checkArgument(tieneCaracterEspecial(contrasenia), new IllegalArgumentException("La contraseña no tiene un caracter especial"));
        Preconditions.checkArgument(!esComun(contrasenia), new IllegalArgumentException("Contraseña demasiado común"));
    }

    private boolean tieneCaracterEspecial(String contrasenia) {
        return Pattern.matches("^(?=.*[@#$%^&+=]).*$", contrasenia);
    }

    private boolean esComun(String contrasenia) {
        Diccionario diezMilMasComunes = new Diccionario("10k-most-common.txt");
        return diezMilMasComunes.contiene(contrasenia);
    }

    public boolean esIgualA(String contraseniaCandidata) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return new Hash(contraseniaCandidata, salt).esIgualA(hash);
    }
}
