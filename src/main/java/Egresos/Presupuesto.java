package Egresos;

import Proveedor.Proveedor;
import com.google.common.base.Preconditions;
import java.util.List;

public class Presupuesto {
    private List<Item> items;
    private Egreso egreso;
    private DocumentoComercial documentoComercial;
    private Proveedor proveedor;

   Presupuesto(Proveedor proveedor, List<Item> items, Egreso egreso, DocumentoComercial documentoComercial) {
        this.proveedor = proveedor;
        this.items = Preconditions.checkNotNull(items, "No se ingresaron items");
        this.egreso = egreso; //la validacion de que el egreso no sea cualquier cosa esta dada por la propia clase Egreso
        this.documentoComercial = documentoComercial;
   }

    public int valorTotal(){
        return items.stream().mapToInt(unItem -> unItem.valor()).sum();
    }

    public Proveedor getProveedor() { return proveedor; }

    public List<Item> getItems() { return items; }
}
