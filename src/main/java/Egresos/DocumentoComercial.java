package Egresos;

import Main.IDGenerator;

import javax.persistence.*;

@Entity
public class DocumentoComercial extends IDGenerator {
    @Enumerated(EnumType.STRING)
    TipoDeDocumentoComercial tipo;

    DocumentoComercial(TipoDeDocumentoComercial tipo){
        this.tipo = tipo;
    }

    public DocumentoComercial() {
        super();
    }
}
