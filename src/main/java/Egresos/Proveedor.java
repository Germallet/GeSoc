package Egresos;

import com.google.common.base.Preconditions;

import java.util.List;

public class Proveedor {

    String nombre;
    int identificador;
    String direccionPostal;

    Proveedor(String nombre, int identificador, String direccionPostal) {
        validarCaracteristicas(nombre, direccionPostal);
        this.nombre = nombre;
        this.identificador = identificador;
        this.direccionPostal = direccionPostal;
    }

    void validarCaracteristicas(String nombre, String direccionPostal) {
        Preconditions.checkNotNull(nombre, "No se ingresó un nombre");
        Preconditions.checkNotNull(direccionPostal, "No se ingresó una direccion");
    }
}
