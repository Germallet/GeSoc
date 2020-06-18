package Egresos;

import Localizacion.DireccionPostal;
import Localizacion.Pais;
import Organizaciones.Organizacion;
import Proveedor.Proveedor;
import Proveedor.Identificador;
import Proveedor.TipoDeID;
import org.junit.*;
import org.mockito.Mockito;

import java.util.*;
import java.time.LocalDate;

import static Proveedor.TipoDeID.DNI;

public class EgresoTest {
    Organizacion organizacion;
    DocumentoComercial documento;
    Proveedor proveedor;
    MedioDePago medioDePago;
    Identificador identificador;
    List<Item> items = new ArrayList<>();
    Egreso egreso;
    Presupuesto presupuesto;


    public Item unItem;
    public Item otroItem;

    @Before
    public void inicializarTest() {
        organizacion = new Organizacion();
        documento= new DocumentoComercial(1,TipoDeDocumentoComercial.FACTURA);
        identificador = new Identificador(42698536, DNI);
        proveedor = new Proveedor("juan", identificador, Mockito.mock(DireccionPostal.class));
        medioDePago = new TarjetaCredito(22345);


        unItem = new Item("item1", 100, TipoDeItem.PRODUCTO);
        otroItem = new Item("item2", 250, TipoDeItem.SERVICIO);

        items.add(unItem);
        items.add(otroItem);
        egreso = new Egreso(organizacion,documento,proveedor,LocalDate.now(),medioDePago,items);
        presupuesto=new Presupuesto(proveedor,items,egreso,documento);
    }

     @Test
    public void elValorTotalEs350() {
        Assert.assertEquals(350, presupuesto.valorTotal());
     }

     @Test
    public void elProveedorDelEgresoNoPuedeSerNulo() {
         Assert.assertThrows(NullPointerException.class, () -> new Egreso(organizacion,documento, null, LocalDate.now(),  medioDePago, items ));
    }
}