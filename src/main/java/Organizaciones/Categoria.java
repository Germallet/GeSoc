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

    public Categoria() {}
    public Categoria(String nombre, boolean permiteEntidadBase, boolean puedeSerDeJuridica, ComportamientoPermitirEgreso permitirEgreso) {
        this.nombre = nombre;
        this.permiteEntidadBase = permiteEntidadBase;
        this.puedeSerDeJuridica = puedeSerDeJuridica;
        this.permitirEgreso = permitirEgreso;
    }

    public boolean permiteEgreso(List<Egreso> egresos, Egreso nuevoEgreso) {
        return permitirEgreso.permiteEgreso(egresos, nuevoEgreso);
    }

    public Categoria(String nombre, boolean permiteEntidadBase, boolean puedeSerDeJuridica) {
        this.nombre = nombre;
        this.permiteEntidadBase = permiteEntidadBase;
        this.puedeSerDeJuridica = puedeSerDeJuridica;
    }

    public boolean getPermiteEntidadBase() { return permiteEntidadBase; }
    public void setPermiteEntidadBase(Boolean permiteEntidadBase) { this.permiteEntidadBase = permiteEntidadBase; }
    public boolean getPuedeSerDeJuridica() {
        return puedeSerDeJuridica;
    }
    public void setPuedeSerDeJuridica(Boolean puedeSerDeJuridica) {
        this.puedeSerDeJuridica = puedeSerDeJuridica;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }
}
