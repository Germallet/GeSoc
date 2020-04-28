package Organizaciones;

public interface Entidad {
}

class Juridica implements Entidad{
    String razonSocial;
    String nombreFicticio;
    int cuit;
    int direccionPostal;
    int codigoDeInscripcion;
    Base entidadBase;

    Juridica(String razonSocial, String nombreFicticio, int cuit, int direccionPostal){
        this.razonSocial = razonSocial;
        this.nombreFicticio = nombreFicticio;
        this.cuit = cuit;
        this.direccionPostal = direccionPostal;
    }

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

    // quizas faltaria agregar si la entidad base tiene que conocer a su entidad juridica
}
