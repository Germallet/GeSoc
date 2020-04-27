package Egresos;

public class Proveedor {
    String nombre;
    String razonSocial;
    int dni;
    String direccionPostal;

    Proveedor(String nombre, String razon, int dni, String direccion){
        this.nombre = nombre;
        this.razonSocial = razon;
        this.dni = dni;
        this.direccionPostal = direccion;
    }
}
