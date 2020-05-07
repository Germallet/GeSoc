package Egresos;
import com.google.common.base.Preconditions;

import java.time.LocalDate;
import java.util.List;

public class Egreso {
    DocumentoComercial documento;
    Proveedor proveedor;
    LocalDate fecha;
    MedioDePago medioDePago;
    List<Item> items;

    Egreso(Proveedor unProveedor, LocalDate fecha, MedioDePago unPago, List<Item> unosItems) {
        validarAtributos(unProveedor, fecha, unPago, unosItems);
        this.fecha = fecha;
        this.proveedor = unProveedor;
        this.medioDePago = unPago;
        this.items = unosItems;
    }

    // como es opcional lo agregue para que si se necesita un documento pueda agregarse de forma no obligatoria
    public void setDocumentoComercial(DocumentoComercial unDocumento){
        documento = unDocumento;
    }

    public int valorTotal(){
        return items.stream().mapToInt(unItem -> unItem.valor()).sum();
    }

    public void validarAtributos(Proveedor unProveedor, LocalDate fecha, MedioDePago unPago, List<Item> unosItems){
        Preconditions.checkNotNull(unProveedor, "No se ingreso un proveedor");
        Preconditions.checkNotNull(fecha, "No se ingreso una fecha");
        Preconditions.checkNotNull(unPago, "No se ingreso un medio de pago");
        Preconditions.checkNotNull(unosItems, "No se ingreso ningun item");
    }
        /*validarNoNulo(unProveedor);
        validarNoNulo(fecha);
        validarNoNulo(unPago);
        validarNoNulo(unosItems);


    void validarNoNulo(Object unAtributo){
        if(unAtributo.equals(null)){
            throw new NuloException("alguno de los campos es nulo");
        }
    }*/
}
