package Seguridad;

// creo que es mejor una clase en vez del Enum porque probablemente los usuarios tengan comportamiento

public interface TipoDeUsuario{
}

class Administrador implements TipoDeUsuario{
}

class Estandar implements TipoDeUsuario{
}

/*public enum TipoDeUsuario {
    Estándar,
    Administrador
}*/
