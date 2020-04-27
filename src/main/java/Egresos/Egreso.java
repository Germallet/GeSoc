package Egresos;

public class Egreso {
    DocumentoComercial documento;
    Proveedor proveedor;
    String fecha;
    Pago pago;
    float valorTotal;
    Item item;

    Egreso(Proveedor unProveedor, String fecha, Pago unPago, float unValor,Item unItem) {
        this.fecha = fecha;
        this.proveedor = unProveedor;
        this.pago = unPago;
        this.valorTotal = unValor;
        this.item = unItem;
    }

}
