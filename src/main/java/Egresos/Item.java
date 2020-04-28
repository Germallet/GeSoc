package Egresos;

public class Item {
    String descripcion;
    int valor;

    public int valor() {
        return this.valor;
    }
}
// se utiliza herencia y no composicion porque no interesa que se transformen
class Producto extends Item{
}

class Servicio extends Item{
}

// estoy considerando que la razon del egreso puede ser la contratacion de un servicio o compra de un producto