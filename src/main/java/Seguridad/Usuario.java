package Seguridad;

import Main.IDGenerator;
import Organizaciones.*;
import com.google.common.base.Preconditions;
import javax.persistence.*;
import java.util.Optional;

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

    public Optional<Entidad> getEntidadConId(String id) {
        try {
            return getOrganizacion().getEntidades().stream().filter(e -> Long.parseLong(id) == e.getId()).findAny();
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
    public Optional<Categoria> getCategoriaConId(String id) {
        try {
            return getOrganizacion().getCategorias().stream().filter(c -> Long.parseLong(id) == c.getId()).findAny();
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
