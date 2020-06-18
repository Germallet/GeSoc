package Egresos;

import Organizaciones.Organizacion;
import Proveedor.Proveedor;
import com.google.common.base.Preconditions;

import java.time.LocalDate;
import java.util.List;

public class Egreso {
    Organizacion organizacion;
    DocumentoComercial documento;
    Proveedor proveedor;
    LocalDate fecha;
    MedioDePago medioDePago;
    List<Item> items;


    Egreso(Organizacion organizacion, DocumentoComercial documento,Proveedor unProveedor, LocalDate fecha, MedioDePago unPago, List<Item> unosItems) {
        this.organizacion = Preconditions.checkNotNull(organizacion, "No se ingreso una organizacion");
        this.documento=documento;
        this.fecha = Preconditions.checkNotNull(fecha, "No se ingreso una fecha");
        this.proveedor = Preconditions.checkNotNull(unProveedor, "No se ingreso un proveedor");
        this.medioDePago = Preconditions.checkNotNull(unPago, "No se ingreso un medio de pago");
        this.items = Preconditions.checkNotNull(unosItems, "No se ingreso ningun item");
    }

    public void setDocumentoComercial(DocumentoComercial unDocumento){
        documento = unDocumento;
    }


}
