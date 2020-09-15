package Proveedor;

import Localizacion.DireccionPostal;
import com.google.common.base.Preconditions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @GeneratedValue
    private long id_proveedor;

    String nombre;
    Identificador identificador;
    DireccionPostal direccionPostal;

    public Proveedor(String nombre, Identificador identificador, DireccionPostal direccionPostal) {
        this.nombre = Preconditions.checkNotNull(nombre, "No se ingreso un nombre");;
        this.identificador = identificador;
        this.direccionPostal = Preconditions.checkNotNull(direccionPostal, "No se ingreso una direccion");
    }
}
