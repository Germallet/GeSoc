package Organizaciones;

import Egresos.Egreso;
import Egresos.Reporte;
import com.google.common.base.Preconditions;

import java.util.List;

public class Entidad {
    List<Egreso> egresos;
    Categoria comportamiento;

    public Reporte generarReporte(){
        return new Reporte(egresos);
    }

    public void validarEgresos(){
        egresos.forEach(egreso -> egreso.validar());
    }
}

class Juridica extends Entidad{
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

class Base extends Entidad{
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
