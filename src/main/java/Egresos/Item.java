package Egresos;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue
    private Long id_item;

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
