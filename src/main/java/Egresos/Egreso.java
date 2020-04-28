package Egresos;
import java.util.List;

public class Egreso {
    DocumentoComercial documento;
    Proveedor proveedor;
    String fecha;
    Pago pago;
    float valorTotal;
    List<Item> items;

    Egreso(Proveedor unProveedor, String fecha, Pago unPago, List<Item> unosItems) {
        this.fecha = fecha;
        this.proveedor = unProveedor;
        this.pago = unPago;
        this.items = unosItems;
    }

    // como es opcional lo agregue para que si se necesita un documento pueda agregarse de forma no obligatoria
    void setDocumentoComercial(DocumentoComercial unDocumento){
        documento = unDocumento;
    }

    float valorTotal(){
        return items.stream().mapToInt(unItem -> unItem.valor()).sum();
    }
}
