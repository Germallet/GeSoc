package Egresos;

import org.junit.*;
import java.util.*;
import java.time.LocalDate;

import static Egresos.TipoDeItem.*;

public class EgresoTest {
    private Egreso egreso;
    private Proveedor proveedor;
    MedioDePago medioDePago;
    List<Item> items = new ArrayList<>();

    public Item unItem;
    public Item otroItem;

    @Before
    public void inicializarTest() {
        proveedor = new Proveedor("juan", 42698536, "almagro");
        medioDePago = new TarjetaCredito(22345);

        unItem = new Item("item1", 100, Producto);
        otroItem = new Item("item2", 250, Servicio);

        items.add(unItem);
        items.add(otroItem);

        egreso = new Egreso(proveedor, LocalDate.now(),  medioDePago, items);
    }

     @Test
    public void elValorTotalEs350() {
        Assert.assertEquals(350, egreso.valorTotal());
     }

     @Test
    public void elProveedorDelEgresoNoPuedeSerNulo() {
         Assert.assertThrows(NullPointerException.class, () -> new Egreso(null, LocalDate.now(),  medioDePago, items ));
    }
}