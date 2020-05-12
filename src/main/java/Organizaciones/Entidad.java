package Organizaciones;

import com.google.common.base.Preconditions;

public interface Entidad {
}

class Juridica implements Entidad{
    String razonSocial;
    String nombreFicticio;
    int CUIT;
    int direccionPostal;
    int codigoDeInscripcion;
    Categoria categoria;

    Juridica(String razonSocial, String nombreFicticio, int CUIT, int direccionPostal, Categoria categoria) {
        validarAtributos(razonSocial, nombreFicticio, categoria);
        this.razonSocial = razonSocial;
        this.nombreFicticio = nombreFicticio;
        this.CUIT = CUIT;
        this.direccionPostal = direccionPostal;
        this.categoria = categoria;
    }

    private void validarAtributos(String razonSocial, String nombreFicticio, Categoria categoria) {
        Preconditions.checkNotNull(razonSocial, "No se ingresó razón social");
        Preconditions.checkNotNull(nombreFicticio, "No se ingresó nombre ficticio");
        Preconditions.checkNotNull(categoria, "No se ingresó categoría");
    }

    public void setCodigoDeInscripcion(int unCodigo) {
        codigoDeInscripcion = unCodigo;
    }
}

class Base implements Entidad{
    String nombreFicticio;
    String descripcion;
    Juridica entidadJuridica;

    Base(String nombreFicticio, String descripcion) {
        Preconditions.checkNotNull(nombreFicticio, "No se ingresó nombre ficticio");
        this.nombreFicticio = nombreFicticio;
        this.descripcion = descripcion;
    }

    public void setEntidadJuridica(Juridica entidadJuridica) {
        this.entidadJuridica = entidadJuridica;
    }
}
