package Seguridad;

import Main.IDGenerator;
import com.google.common.base.Preconditions;

import javax.persistence.*;

@Entity

public class Usuario extends IDGenerator {
    @Enumerated(EnumType.STRING)
    TipoDeUsuario tipo;
    String nombre;

    @OneToOne
    BandejaDeMensajes bandejaDeMensajes;

    @OneToOne
    @JoinColumn(name="contrasenia_id",referencedColumnName ="id")
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

    public BandejaDeMensajes getBandejaDeMensajes() { return bandejaDeMensajes; }
}
