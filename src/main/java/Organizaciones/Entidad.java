package Organizaciones;

public interface Entidad {
}

class Juridica implements Entidad{
    String razonSocial;
    String nombreFicticio;
    int CUIT;
    int direccionPostal;
    int codigoDeInscripcion;
    Base entidadBase;
    Categoria categoria;

    Juridica(String razonSocial, String nombreFicticio, int CUIT, int direccionPostal, Categoria categoria){
        this.razonSocial = razonSocial;
        this.nombreFicticio = nombreFicticio;
        this.CUIT = CUIT;
        this.direccionPostal = direccionPostal;
        this.categoria = categoria;
    }

    // en el enunciado se especifica como optativo
    void setCodigoDeInscripcion(int unCodigo){
        codigoDeInscripcion = unCodigo;
    }

    void setEntidadBase(Base unaEntidad){
        entidadBase = unaEntidad;
    }
}

class Base implements Entidad{
    String nombreFicticio;
    String descripcion;

    // quizas faltaria agregar si la entidad base tiene que conocer a su entidad juridica o encontrar
    // una forma de restringir que solo pueda pertenecer a una
}
