package Egresos;

import java.util.ArrayList;

public class Item {
    ArrayList<Producto> productos;
    String detalle;
    public int valorTotal(){
        return productos.stream().mapToInt(producto -> producto.valor()).sum();
    }

}

class Producto{
    String descripcion;
    int valor;
    public int valor(){
        return this.valor;
    }
}
