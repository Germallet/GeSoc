package Proveedor;

import Localizacion.DireccionPostal;
import Main.IDGenerator;
import com.google.common.base.Preconditions;

import javax.persistence.*;

@Entity
public class Proveedor extends IDGenerator {

    String nombre;
    @OneToOne(cascade = CascadeType.ALL)
    Identificador identificador;
    @OneToOne(cascade = CascadeType.ALL)
    DireccionPostal direccionPostal;

    public Proveedor(String nombre, Identificador identificador, DireccionPostal direccionPostal) {
        this.nombre = Preconditions.checkNotNull(nombre, "No se ingreso un nombre");;
        this.identificador = identificador;
        this.direccionPostal = Preconditions.checkNotNull(direccionPostal, "No se ingreso una direccion");
    }

}
