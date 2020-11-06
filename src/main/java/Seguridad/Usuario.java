package Seguridad;

import Main.IDGenerator;
import com.google.common.base.Preconditions;

import javax.persistence.*;

@Entity
public class Usuario extends IDGenerator {
    @Enumerated(EnumType.ORDINAL)
    TipoDeUsuario tipo;
    String nombre;

    @Embedded
    BandejaDeMensajes bandejaDeMensajes;

    @Embedded
    Contrasenia contrasenia;

    public Usuario(TipoDeUsuario tipo, String nombre, Contrasenia contrasenia) {
        Preconditions.checkArgument(!nombre.isEmpty(), new IllegalArgumentException("Nombre de usuario vacío"));
        this.tipo = tipo;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }

    public Usuario() {
        super();
    }

    public boolean validarLogin(String username, String password) {
        return nombre.equals(username) && contrasenia.esIgualA(password);
    }

    public String getNombre() { return nombre; }

    public BandejaDeMensajes getBandejaDeMensajes() { return bandejaDeMensajes; }
}
