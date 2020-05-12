package Egresos;

public class Item {
    String descripcion;
    int valor;
    TipoDeItem tipo;

    Item(String descripcion, int valor, TipoDeItem tipo){
        this.descripcion = descripcion;
        this.valor = valor;
        this.tipo = tipo;
    }

    public int valor() {
        return this.valor;
    }
}
