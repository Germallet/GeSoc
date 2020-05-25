package Proveedor;

import com.google.common.base.Preconditions;

public class Proveedor {

    String nombre;
    Identificador identificador;
    String direccionPostal;

    Proveedor(String nombre, Identificador identificador, String direccionPostal) {
        Preconditions.checkNotNull(nombre, "No se ingresó un nombre");
        Preconditions.checkNotNull(direccionPostal, "No se ingresó una direccion");
        this.nombre = nombre;
        this.identificador = identificador;
        this.direccionPostal = direccionPostal;
    }
}
