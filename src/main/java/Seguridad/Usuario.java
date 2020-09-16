package Seguridad;

import Main.IDGenerator;
import com.google.common.base.Preconditions;

import javax.persistence.*;

@Entity
public class Usuario extends IDGenerator {
    @Enumerated(EnumType.STRING)
    TipoDeUsuario tipo;
    String nombre;
    @Transient
    Contrasenia contrasenia;
    @OneToOne
    BandejaDeMensajes bandejaDeMensajes;

    public Usuario(TipoDeUsuario tipo, String nombre, Contrasenia contrasenia) {
        Preconditions.checkArgument(!nombre.isEmpty(), new IllegalArgumentException("Nombre de usuario vacío"));
        this.tipo = tipo;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }

    public Usuario() {
        super();
    }

    public BandejaDeMensajes getBandejaDeMensajes() { return bandejaDeMensajes; }
}
