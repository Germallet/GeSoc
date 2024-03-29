package Egresos;

import Main.IDGenerator;
import Proveedor.Proveedor;
import com.google.common.base.Preconditions;

import javax.persistence.*;
import java.util.List;

@Entity
public class Presupuesto extends IDGenerator {
    @OneToMany
    @JoinColumn(name="presupuesto_id")
    private List<Item> items;
    @OneToOne
    private DocumentoComercial documentoComercial;
    @ManyToOne
    private Proveedor proveedor;

   public Presupuesto(Proveedor proveedor, List<Item> items, DocumentoComercial documentoComercial) {
        this.proveedor = proveedor;
        this.items = Preconditions.checkNotNull(items, "No se ingresaron items");
        this.documentoComercial = documentoComercial;
   }

    public Presupuesto() {
        super();
    }

    public int valorTotal(){
        return items.stream().mapToInt(unItem -> unItem.valor()).sum();
    }

    public Proveedor getProveedor() { return proveedor; }

    public List<Item> getItems() { return items; }
}
