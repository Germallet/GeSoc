package Proveedor;

import com.google.common.base.Preconditions;

public class Proveedor {

    String nombre;
    Identificador identificador;
    String direccionPostal;

    public Proveedor(String nombre, Identificador identificador, String direccionPostal) {
        Preconditions.checkNotNull(nombre, "No se ingreso un nombre");
        Preconditions.checkNotNull(direccionPostal, "No se ingreso una direccion");
        this.nombre = nombre;
        this.identificador = identificador;
        this.direccionPostal = direccionPostal;
    }
}
