package Proveedor;

import Localizacion.DireccionPostal;
import com.google.common.base.Preconditions;

public class Proveedor {

    String nombre;
    Identificador identificador;
    DireccionPostal direccionPostal;

    public Proveedor(String nombre, Identificador identificador, DireccionPostal direccionPostal) {
        this.nombre = Preconditions.checkNotNull(nombre, "No se ingreso un nombre");;
        this.identificador = identificador;
        this.direccionPostal = Preconditions.checkNotNull(direccionPostal, "No se ingreso una direccion");
    }
}
