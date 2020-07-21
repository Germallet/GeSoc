package Egresos;

import Proveedor.Proveedor;
import com.google.common.base.Preconditions;
import java.util.List;

public class Presupuesto {
    private List<Item> items;
    private DocumentoComercial documentoComercial;
    private Proveedor proveedor;

   Presupuesto(Proveedor proveedor, List<Item> items, DocumentoComercial documentoComercial) {
        this.proveedor = proveedor;
        this.items = Preconditions.checkNotNull(items, "No se ingresaron items");
        this.documentoComercial = documentoComercial;
   }

    public int valorTotal(){
        return items.stream().mapToInt(unItem -> unItem.valor()).sum();
    }

    public Proveedor getProveedor() { return proveedor; }

    public List<Item> getItems() { return items; }
}
