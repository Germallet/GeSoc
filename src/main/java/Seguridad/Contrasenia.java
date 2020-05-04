package Seguridad;

import com.google.common.base.Preconditions;

import java.security.*;
import java.security.spec.*;
import java.util.regex.*;

public class Contrasenia {

    private Hash hash;
    private Salt salt;

    public Contrasenia(String contrasenia) throws InvalidKeySpecException, NoSuchAlgorithmException {
        ValidarPrecondiciones(contrasenia);
        salt = new Salt();
        hash = new Hash(contrasenia, salt);
    }

    private void ValidarPrecondiciones(String contrasenia) {
        Preconditions.checkArgument(contrasenia.length() >= 8, new IllegalArgumentException("Contraseña demasiado corta"));
        Preconditions.checkArgument(!EsComun(contrasenia), new IllegalArgumentException("Contraseña demasiado común"));
        Preconditions.checkArgument(tieneCaracterEspecial(contrasenia),new IllegalArgumentException("La contraseña no tiene un caracter especial"));
    }

    private boolean EsComun(String contrasenia) {
        // TODO
        return false;
    }

    public boolean EsIgualA(String contraseniaCandidata) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return new Hash(contraseniaCandidata, salt).EsIgualA(hash);
    }
    public boolean tieneCaracterEspecial(String contrasenia){
        return Pattern.matches("^(?=.*[@#$%^&+=]).*$",contrasenia);
    }
    }
