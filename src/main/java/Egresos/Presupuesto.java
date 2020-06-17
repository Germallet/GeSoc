package Egresos;

import Organizaciones.Organizacion;
import Proveedor.Proveedor;
import com.google.common.base.Preconditions;

import java.time.LocalDate;
import java.util.List;

public class Presupuesto{

    String detalles;
    Egreso egreso;
    List<DocumentoComercial> documentosComerciales;
    int total;


   Presupuesto(String detalles,Egreso egreso,List<DocumentoComercial> documentoComercials,int total) {
        this.detalles = Preconditions.checkNotNull(detalles, "No se ingreso un detalle");
        this.egreso = egreso; //la validacion de que el egreso no sea cualquier cosa esta dada por la propia clase Egreso
        this.documentosComerciales = documentoComercials;
        this.total = Preconditions.checkNotNull(total,"El valor total no es valido");
    }

}
