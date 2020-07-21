package Organizaciones;

import Proveedor.Proveedor;

public interface Etiqueta {

}

class EtiquetaNombreProveedor implements Etiqueta{
    Proveedor proveedor;

    public EtiquetaNombreProveedor(Proveedor proveedor){
        this.proveedor = proveedor;
    }

}

class EtiquetaSimple implements Etiqueta{
    String nombre;

    public EtiquetaSimple(String nombre){
        this.nombre = nombre;
    }


}


