package Organizaciones;

import Egresos.Egreso;
import Egresos.Reporte;
import com.google.common.base.Preconditions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "entidades")
public abstract class Entidad {

    @Id
    @GeneratedValue
    private long id_entidad;

    protected List<Egreso> egresos;

    public Reporte generarReporte() {
        return new Reporte(egresos);
    }

    public void validarEgresos() {
        egresos.forEach(egreso -> egreso.validar());
    }

    public void agregarEgreso(Egreso nuevoEgreso) {
        Preconditions.checkArgument(permiteEgreso(nuevoEgreso));
        egresos.add(nuevoEgreso);
    }
    protected abstract boolean permiteEgreso(Egreso nuevoEgreso);
}

class Juridica extends Entidad {
    String razonSocial;
    String nombreFicticio;
    int CUIT;
    int direccionPostal;
    int codigoDeInscripcion;
    CategoriaEntidadJuridica categoria;

    Juridica(String razonSocial, String nombreFicticio, int CUIT, int direccionPostal, CategoriaEntidadJuridica categoria) {
        this.razonSocial = Preconditions.checkNotNull(razonSocial, "No se ingresó razón social");
        this.nombreFicticio = Preconditions.checkNotNull(nombreFicticio, "No se ingresó nombre ficticio");
        this.CUIT = CUIT;
        this.direccionPostal = direccionPostal;
        this.categoria = Preconditions.checkNotNull(categoria, "No se ingresó categoría");
    }

    public void setCodigoDeInscripcion(int unCodigo) {
        codigoDeInscripcion = unCodigo;
    }

    public boolean permiteEntidadBase() {
        return categoria.permiteEntidadBase();
    }

    protected boolean permiteEgreso(Egreso nuevoEgreso) {
        return categoria.permiteEgreso(egresos, nuevoEgreso);
    }
}

class Base extends Entidad {
    String nombreFicticio;
    String descripcion;
    Juridica entidadJuridica;
    CategoriaEntidadBase categoria;

    Base(String nombreFicticio, String descripcion, CategoriaEntidadBase categoria) {
        this.nombreFicticio = Preconditions.checkNotNull(nombreFicticio, "No se ingresó nombre ficticio");
        this.descripcion = descripcion;
        this.categoria = Preconditions.checkNotNull(categoria, "No se ingresó categoría");
    }

    public void setEntidadJuridica(Juridica entidadJuridica) {
        if (categoria.puedeSerDeJuridica() && entidadJuridica.permiteEntidadBase())
            this.entidadJuridica = entidadJuridica;
    }

    protected boolean permiteEgreso(Egreso nuevoEgreso) {
        return categoria.permiteEgreso(egresos, nuevoEgreso);
    }
}
