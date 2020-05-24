package Seguridad;

import Seguridad.ValidadorDeContrasenia.ValidadorDeContrasenia;
import java.util.Collection;

public class Contrasenia {

    private Hash hash;
    private Salt salt;

    public Contrasenia(String contrasenia, Collection<ValidadorDeContrasenia> validadores) {
        validadores.forEach( (ValidadorDeContrasenia validador) -> { validador.validar(contrasenia); });
        salt = new Salt();
        hash = new Hash(contrasenia, salt);
    }

    public boolean esIgualA(String contraseniaCandidata) {
        return new Hash(contraseniaCandidata, salt).esIgualA(hash);
    }
}
