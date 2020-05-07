package Egresos;

// puede ser tanto una interface como una clase, eso lo van a determinar los requerimientos de las proximas entregas

public class DocumentoComercial {
    int numero;
    TipoDeDocumentoComercial tipo;

    DocumentoComercial(int numero, TipoDeDocumentoComercial tipo){
        this.numero = numero;
        this.tipo = tipo;
    }
}
