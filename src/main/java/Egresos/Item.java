package Egresos;


import Main.IDGenerator;

import javax.persistence.*;

@Entity
public class Item extends IDGenerator {

    String descripcion;
    int valor;
    @Enumerated(EnumType.STRING)
    TipoDeItem tipo;

    Item(String descripcion, int valor, TipoDeItem tipo){
        this.descripcion = descripcion;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Item() {
        super();
    }

    public int valor() {
        return this.valor;
    }
}
