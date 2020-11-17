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
    @JoinColumn(name="entidad_id")
    protected List<Egreso> egresos;
    @ManyToOne
    Categoria categoria;

    String nombreFicticio;

    public Reporte generarReporte() {
        return new Reporte(egresos);
    }

    public void validarEgresos() {
        egresos.forEach(Egreso::validar);
    }

    public void agregarEgreso(Egreso nuevoEgreso) {
        if (categoria != null)
            Preconditions.checkArgument(categoria.permiteEgreso(egresos, nuevoEgreso));
        egresos.add(nuevoEgreso);
    }

    public List<Egreso> getEgresos() { return egresos; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public String getNombreFicticio() { return nombreFicticio; }
    public void setNombreFicticio(String nombreFicticio) { this.nombreFicticio = nombreFicticio; }
    public abstract String getTipo();

    public Boolean perteneceACategoria(Categoria categoria) { return this.categoria == categoria; }
}
