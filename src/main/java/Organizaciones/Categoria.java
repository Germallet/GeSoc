package Organizaciones;

import Egresos.Egreso;
import java.util.List;

public class Categoria {
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
