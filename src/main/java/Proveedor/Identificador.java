package Proveedor;

import javax.persistence.*;

@Embeddable
public class Identificador {
    @Column(name="numeroIdentificador")
    int numero;
    @Enumerated
    @Column(name="tipoIdentificador")
    TipoDeID tipo;

    public Identificador(int numero, TipoDeID tipo){
        this.numero = numero;
        this.tipo = tipo;
    }

    public Identificador() {
        super();
    }
}
