package Egresos;
import java.util.List;

public class Egreso {
    DocumentoComercial documento;
    Proveedor proveedor;
    String fecha;
    MedioDePago medioDePago;
    List<Item> items;

    Egreso(Proveedor unProveedor, String fecha, MedioDePago unPago, List<Item> unosItems) {
        validarAtributos(unProveedor, fecha, unPago, unosItems);
        this.fecha = fecha;
        this.proveedor = unProveedor;
        this.medioDePago = unPago;
        this.items = unosItems;
    }

    // como es opcional lo agregue para que si se necesita un documento pueda agregarse de forma no obligatoria
    void setDocumentoComercial(DocumentoComercial unDocumento){
        documento = unDocumento;
    }

    int valorTotal(){
        return items.stream().mapToInt(unItem -> unItem.valor()).sum();
    }

    void validarAtributos(Proveedor unProveedor, String fecha, MedioDePago unPago, List unosItems){
        validarNoNulo(unProveedor);
        validarNoNulo(fecha);
        validarNoNulo(unPago);
        validarNoNulo(unosItems);
    }

    // este metodo me tira un error en la comparacion con null y no entiendo por que
    void validarNoNulo(Object unAtributo){
        if(unAtributo.equals(null)){
            throw new NuloException("alguno de los campos es nulo");
        }
    }
}
