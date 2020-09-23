package Egresos;

import Main.IDGenerator;

import javax.persistence.*;

@Entity
public class DocumentoComercial extends IDGenerator {

    @Enumerated(EnumType.ORDINAL)
    TipoDeDocumentoComercial tipo;

    public DocumentoComercial(TipoDeDocumentoComercial tipo){
        this.tipo = tipo;
    }

    public DocumentoComercial() {
        super();
    }
}
