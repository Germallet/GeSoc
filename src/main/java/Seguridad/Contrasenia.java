package Seguridad;

import Seguridad.ValidadorDeContrasenia.ValidadorDeContrasenia;
import javax.persistence.*;
import java.util.Collection;

@Embeddable
public class Contrasenia {
    @Embedded
    private Hash hash;
    @Embedded
    private Salt salt;

    public Contrasenia(){}

    public Contrasenia(String contrasenia, Collection<ValidadorDeContrasenia> validadores) {
        validadores.forEach( (ValidadorDeContrasenia validador) -> { validador.validar(contrasenia); });
        salt = new Salt();
        hash = new Hash(contrasenia, salt);
    }

    public boolean esIgualA(String contraseniaCandidata) {
        return new Hash(contraseniaCandidata, salt).esIgualA(hash);
    }
}
