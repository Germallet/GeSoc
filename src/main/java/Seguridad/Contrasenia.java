package Seguridad;

import Main.IDGenerator;
import Seguridad.ValidadorDeContrasenia.ValidadorDeContrasenia;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Collection;
import javax.persistence.Entity;


@Entity
@Table(name="Password")
public class Contrasenia extends IDGenerator {

    /*private Hash hash;
    private Salt salt;*/
    private byte[] hash;
    private byte[] salt;
    private Salt saltobject=new Salt();
    private Hash hashobject;

    public Contrasenia(){}

    public Contrasenia(String contrasenia, Collection<ValidadorDeContrasenia> validadores) {
        validadores.forEach( (ValidadorDeContrasenia validador) -> { validador.validar(contrasenia); });

        salt = saltobject.obtenerBytes();

        hashobject=new Hash(contrasenia,saltobject);
        hash = hashobject.obtenerBytes();
    }

    public boolean esIgualA(String contraseniaCandidata) {
        return new Hash(contraseniaCandidata, saltobject).esIgualA(hashobject);
    }
}
