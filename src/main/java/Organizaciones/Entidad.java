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
        this.razonSocial = Preconditions.checkNotNull(razonSocial, "No se ingresó razón social");
        this.nombreFicticio = Preconditions.checkNotNull(nombreFicticio, "No se ingresó nombre ficticio");
        this.CUIT = CUIT;
        this.direccionPostal = direccionPostal;
        this.categoria = Preconditions.checkNotNull(categoria, "No se ingresó categoría");
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
        this.nombreFicticio = Preconditions.checkNotNull(nombreFicticio, "No se ingresó nombre ficticio");
        this.descripcion = descripcion;
    }

    public void setEntidadJuridica(Juridica entidadJuridica) {
        this.entidadJuridica = entidadJuridica;
    }
}
