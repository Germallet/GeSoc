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

    Egreso(Organizacion organizacion, Proveedor unProveedor, LocalDate fecha, MedioDePago unPago, List<Item> unosItems) {
        validarAtributos(organizacion, unProveedor, fecha, unPago, unosItems);
        this.organizacion = organizacion;
        this.fecha = fecha;
        this.proveedor = unProveedor;
        this.medioDePago = unPago;
        this.items = unosItems;
    }

    public void setDocumentoComercial(DocumentoComercial unDocumento){
        documento = unDocumento;
    }

    public int valorTotal(){
        return items.stream().mapToInt(unItem -> unItem.valor()).sum();
    }

    private void validarAtributos(Organizacion organizacion, Proveedor unProveedor, LocalDate fecha, MedioDePago unPago, List<Item> unosItems) {
        Preconditions.checkNotNull(organizacion, "No se ingresó una organización");
        Preconditions.checkNotNull(unProveedor, "No se ingresó un proveedor");
        Preconditions.checkNotNull(fecha, "No se ingresó una fecha");
        Preconditions.checkNotNull(unPago, "No se ingresó un medio de pago");
        Preconditions.checkNotNull(unosItems, "No se ingresó ningun item");
    }
}
