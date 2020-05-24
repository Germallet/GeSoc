package Seguridad;

import com.google.common.base.Preconditions;

public class Usuario {
    TipoDeUsuario tipo;
    String nombre;
    Contrasenia contrasenia;

    public Usuario(TipoDeUsuario tipo, String nombre, String contrasenia) {
        Preconditions.checkArgument(!nombre.isEmpty(), new IllegalArgumentException("Nombre de usuario vacío"));
        this.tipo = tipo;
        this.nombre = nombre;
        this.contrasenia = new Contrasenia(contrasenia);
    }
}
