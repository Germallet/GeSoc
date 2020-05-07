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

// use composicion porque seguramente nos interese tratar al tipo polimorficamente, igualmente deberia revisarse segun los proximos enunciados