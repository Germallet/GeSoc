package Seguridad;

import com.google.common.base.Preconditions;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Usuario {
    TipoDeUsuario tipo;
    String nombre;
    Contrasenia contrasenia;

    public Usuario(TipoDeUsuario tipo, String nombre, String contrasenia) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Preconditions.checkArgument(!nombre.isEmpty(), new IllegalArgumentException("Nombre de usuario vacío"));
        this.tipo = tipo;
        this.contrasenia = new Contrasenia(contrasenia);
    }
}
