package Organizaciones;

import Egresos.Egreso;
import Egresos.Reporte;
import Main.IDGenerator;
import com.google.common.base.Preconditions;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Entidad extends IDGenerator {

    @OneToMany
    protected List<Egreso> egresos;
    @ManyToOne
    Categoria categoria;

    public Reporte generarReporte() {
        return new Reporte(egresos);
    }

    public void validarEgresos() {
        egresos.forEach(egreso -> egreso.validar());
    }

    public void agregarEgreso(Egreso nuevoEgreso) {
        Preconditions.checkArgument(categoria.permiteEgreso(egresos, nuevoEgreso));
        egresos.add(nuevoEgreso);
    }
}

@Entity
class Juridica extends Entidad {
    String razonSocial;
    String nombreFicticio;
    int CUIT;
    int direccionPostal;
    int codigoDeInscripcion;

    public Juridica(String razonSocial, String nombreFicticio, int CUIT, int direccionPostal, Categoria categoria) {
        this.razonSocial = Preconditions.checkNotNull(razonSocial, "No se ingresó razón social");
        this.nombreFicticio = Preconditions.checkNotNull(nombreFicticio, "No se ingresó nombre ficticio");
        this.CUIT = CUIT;
        this.direccionPostal = direccionPostal;
        this.categoria = Preconditions.checkNotNull(categoria, "No se ingresó categoría");
    }

    public Juridica() {
        super();
    }

    public void setCodigoDeInscripcion(int unCodigo) {
        codigoDeInscripcion = unCodigo;
    }

    public boolean permiteEntidadBase() {
        return categoria.permiteEntidadBase();
    }
}

@Entity
class Base extends Entidad {
    String nombreFicticio;
    String descripcion;
    @ManyToOne
    Juridica entidadJuridica;

    public Base(String nombreFicticio, String descripcion, Categoria categoria) {
        this.nombreFicticio = Preconditions.checkNotNull(nombreFicticio, "No se ingresó nombre ficticio");
        this.descripcion = descripcion;
        this.categoria = Preconditions.checkNotNull(categoria, "No se ingresó categoría");
    }

    public Base() {
        super();
    }

    public void setEntidadJuridica(Juridica entidadJuridica) {
        if (categoria.puedeSerDeJuridica() && entidadJuridica.permiteEntidadBase())
            this.entidadJuridica = entidadJuridica;
    }
}
