package Organizaciones;

import com.google.common.base.Preconditions;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Base extends Entidad {
    String descripcion;
    @ManyToOne
    Juridica entidadJuridica;

    public Base(String nombreFicticio, String descripcion, Categoria categoria) {
        this.nombreFicticio = Preconditions.checkNotNull(nombreFicticio, "No se ingresó nombre ficticio");
        this.descripcion = descripcion;
        this.categoria = categoria; //Preconditions.checkNotNull(categoria, "No se ingresó categoría");
    }

    public Base() {
        super();
    }

    public void setEntidadJuridica(Juridica entidadJuridica) {
        if ((categoria == null || categoria.getPuedeSerDeJuridica()) && entidadJuridica.permiteEntidadBase())
            this.entidadJuridica = entidadJuridica;
    }

    public String getTipo() { return "Base"; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
