package Egresos;

import Localizacion.DireccionPostal;
import Proveedor.Proveedor;
import Proveedor.Identificador;
import org.junit.*;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

import static Proveedor.TipoDeID.DNI;

public class PresupuestoTest {
    Presupuesto presupuesto;

    @Before
    public void inicializarTest() {
        DocumentoComercial documento= new DocumentoComercial(1,TipoDeDocumentoComercial.FACTURA);
        Proveedor proveedor = new Proveedor("juan", new Identificador(0, DNI), Mockito.mock(DireccionPostal.class));

        List<Item> items = new ArrayList<>();
        items.add(new Item("itemA", 100, TipoDeItem.PRODUCTO));
        items.add(new Item("itemB", 250, TipoDeItem.SERVICIO));

        presupuesto = new Presupuesto(proveedor, items, documento);
    }

    @Test
    public void elValorTotalEs350() {
        Assert.assertEquals(350, presupuesto.valorTotal());
    }
}
