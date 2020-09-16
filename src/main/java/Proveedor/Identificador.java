package Proveedor;

import Main.IDGenerator;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
public class Identificador extends IDGenerator {
    int numero;
    @Enumerated
    TipoDeID tipo;

    public Identificador(int numero, TipoDeID tipo){
        this.numero = numero;
        this.tipo = tipo;
    }

    public Identificador() {
        super();
    }
}
