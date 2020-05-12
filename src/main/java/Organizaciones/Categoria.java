package Organizaciones;

public interface Categoria {
}

class Empresa implements Categoria {
    Clasificacion clasificacion;

    Empresa(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }
}

class OSC implements Categoria {
}
