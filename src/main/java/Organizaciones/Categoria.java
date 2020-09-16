package Organizaciones;

import Egresos.Egreso;
import Main.IDGenerator;
import Organizaciones.ComportamientoPermitirEgreso.ComportamientoPermitirEgreso;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categoria extends IDGenerator {

    String nombre;
    boolean permiteEntidadBase;
    boolean puedeSerDeJuridica;

    @OneToOne
    ComportamientoPermitirEgreso permitirEgreso;

    public boolean permiteEgreso(List<Egreso> egresos, Egreso nuevoEgreso) {
        return permitirEgreso.permiteEgreso(egresos, nuevoEgreso);
    }

    public boolean permiteEntidadBase() {
        return permiteEntidadBase;
    }

    public boolean puedeSerDeJuridica() {
        return puedeSerDeJuridica;
    }
}
