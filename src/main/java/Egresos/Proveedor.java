package Egresos;

// habria que evaluar que si cuando en el enunciado hablar de proveedor o prestador de servicios se refiere a dos tipos de destinatarios de mis egresos

import com.google.common.base.Preconditions;

import java.util.List;

public class Proveedor {
    String nombre;
    int dni;
    String direccionPostal;

    Proveedor(String nombre, int dni, String direccion){
        this.nombre = nombre;
        this.dni = dni;
        this.direccionPostal = direccion;
    }

    //no se si esta bien agregar asi o seria duplicated code
    void validarCaracteristicas(String nombre, int dni, String direccionPostal){
        Preconditions.checkNotNull(nombre, "No se ingreso un nombre");
        Preconditions.checkNotNull(dni, "No se ingreso una dni");
        Preconditions.checkNotNull(direccionPostal, "No se ingreso una direccion");
    }

}
