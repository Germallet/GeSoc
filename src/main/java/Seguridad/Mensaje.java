package Seguridad;

import Main.IDGenerator;
import javax.persistence.Entity;

@Entity
public class Mensaje extends IDGenerator {
    String contenido;

    public Mensaje(String contenido) {
        this.contenido = contenido;
    }

    public Mensaje() {
        super();
    }
}
