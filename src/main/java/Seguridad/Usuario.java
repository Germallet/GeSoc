package Seguridad;

import Main.IDGenerator;
import Organizaciones.Organizacion;
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

    @OneToOne
    Organizacion organizacion;

    public Usuario(TipoDeUsuario tipo, String nombre, Contrasenia contrasenia) {
        Preconditions.checkArgument(!nombre.isEmpty(), new IllegalArgumentException("Nombre de usuario vacío"));
        this.tipo = tipo;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.organizacion = new Organizacion();
    }

    public Usuario() {
        super();
    }

    public boolean validarLogin(String username, String password) {
        return nombre.equals(username) && contrasenia.esIgualA(password);
    }

    public String getNombre() { return nombre; }

    public Organizacion getOrganizacion() { return organizacion; }

    public BandejaDeMensajes getBandejaDeMensajes() { return bandejaDeMensajes; }
}
