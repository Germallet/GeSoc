package Organizaciones;

import Main.IDGenerator;
import javax.persistence.Entity;

@Entity
public class Etiqueta extends IDGenerator {
    String nombre;

    public Etiqueta(String nombre){
        this.nombre = nombre;
    }

    public Etiqueta() {
        super();
    }
}
