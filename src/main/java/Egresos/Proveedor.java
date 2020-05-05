package Egresos;

// habria que evaluar que si cuando en el enunciado hablar de proveedor o prestador de servicios se refiere a dos tipos de destinatarios de mis egresos

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
       validarNoNulo(nombre);
        validarNoNulo(dni);
        validarNoNulo(direccionPostal);
    }
    void validarNoNulo(Object unAtributo){
        if(unAtributo.equals(null)){
            throw new NuloException("alguno de los campos es nulo");
        }
}}
