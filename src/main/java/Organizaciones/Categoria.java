package Organizaciones;

import Egresos.Egreso;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue
    private long id_categoria;

    String nombre;
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    int montoMaximoEgresos = 0;
    public boolean permiteEgreso(List<Egreso> egresos, Egreso nuevoEgreso) {
        return egresos.stream().mapToInt(egreso -> egreso.valorTotal()).sum() + nuevoEgreso.valorTotal() <= montoMaximoEgresos;
    }
}

class CategoriaEntidadJuridica extends Categoria {
    boolean permiteEntidadBase;
    public boolean permiteEntidadBase() {
        return permiteEntidadBase;
    }
}

class CategoriaEntidadBase extends Categoria {
    boolean puedeSerDeJuridica;
    public boolean puedeSerDeJuridica() {
        return puedeSerDeJuridica;
    }
}
