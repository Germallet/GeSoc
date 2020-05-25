package Egresos;

import Organizaciones.Organizacion;
import Proveedor.Proveedor;
import Proveedor.Identificador;
import Proveedor.TipoDeID;
import org.junit.*;
import java.util.*;
import java.time.LocalDate;

import static Proveedor.TipoDeID.DNI;

public class EgresoTest {
    private Organizacion organizacion;
    private Egreso egreso;
    private Proveedor proveedor;
    MedioDePago medioDePago;
    Identificador identificador;
    List<Item> items = new ArrayList<>();

    public Item unItem;
    public Item otroItem;

    @Before
    public void inicializarTest() {
        organizacion = new Organizacion();
        identificador = new Identificador(42698536, DNI);
        proveedor = new Proveedor("juan", identificador, "almagro");
        medioDePago = new TarjetaCredito(22345);

        unItem = new Item("item1", 100, TipoDeItem.PRODUCTO);
        otroItem = new Item("item2", 250, TipoDeItem.SERVICIO);

        items.add(unItem);
        items.add(otroItem);

        egreso = new Egreso(organizacion, proveedor, LocalDate.now(),  medioDePago, items);
    }

     @Test
    public void elValorTotalEs350() {
        Assert.assertEquals(350, egreso.valorTotal());
     }

     @Test
    public void elProveedorDelEgresoNoPuedeSerNulo() {
         Assert.assertThrows(NullPointerException.class, () -> new Egreso(organizacion, null, LocalDate.now(),  medioDePago, items ));
    }
}