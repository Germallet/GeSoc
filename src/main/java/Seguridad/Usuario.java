package Seguridad;

import Seguridad.ValidadorDeContrasenia.ValidadorDeContrasenia;
import com.google.common.base.Preconditions;

import java.util.Collection;

public class Usuario {
    TipoDeUsuario tipo;
    String nombre;
    Contrasenia contrasenia;

    public Usuario(TipoDeUsuario tipo, String nombre, String contrasenia, Collection<ValidadorDeContrasenia> validadoresDeContrasenia) {
        Preconditions.checkArgument(!nombre.isEmpty(), new IllegalArgumentException("Nombre de usuario vacío"));
        this.tipo = tipo;
        this.nombre = nombre;
        this.contrasenia = new Contrasenia(contrasenia, validadoresDeContrasenia);
    }
}
