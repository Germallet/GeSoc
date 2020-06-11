package Proveedor;

import com.google.common.base.Preconditions;

public class Proveedor {

    String nombre;
    Identificador identificador;
    String direccionPostal;


    // int elaborarPresupuesto(){}

    public Proveedor(String nombre, Identificador identificador, String direccionPostal) {
        this.nombre = Preconditions.checkNotNull(nombre, "No se ingreso un nombre");;
        this.identificador = identificador;
        this.direccionPostal = Preconditions.checkNotNull(direccionPostal, "No se ingreso una direccion");;
    }

}
