package Egresos;

// puede ser tanto una interface como una clase, eso lo van a determinar los requerimientos de las proximas entregas

public interface DocumentoComercial {
}

class Factura implements DocumentoComercial{ // el tipo ya se conoce por la class
    int numero;
}
