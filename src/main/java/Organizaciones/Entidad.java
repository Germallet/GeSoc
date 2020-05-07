package Organizaciones;

import static java.util.Objects.isNull;

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
     //TODO VALIDAR
    // en el enunciado se especifica como optativo
    public void setCodigoDeInscripcion(int unCodigo){
        codigoDeInscripcion = unCodigo;
    }

    public void setEntidadBase(Base unaEntidad){
        validarDisponibilidadDeEntidad(unaEntidad);
        entidadBase = unaEntidad;
    }

    private void validarDisponibilidadDeEntidad(Base unaEntidad) {
        if(unaEntidad.perteneceAUnaJuridica()){
            throw new EntidadBaseException("la entidad base ya pertenece a una juridica");
        }
    }
}

class Base implements Entidad{
    String nombreFicticio;
    String descripcion;
    Juridica entidadJuridica; // a la que pertenece

    Base(String nombreFicticio, String descripcion){
        this.nombreFicticio=nombreFicticio;
        this.descripcion=descripcion;

    }

    public void setEntidadJuridica(Juridica entidadJuridica){
        this.entidadJuridica=entidadJuridica;
    }
    public boolean perteneceAUnaJuridica(){
        return !isNull(entidadJuridica);
    }
}

// agregamos como atributo a la entidad juridica a la que pertenece para poder asegurarnos que otra no pueda
// tenerla como base (segun el enunciado, una entidad base puede pertenecer a solo una juridica)
