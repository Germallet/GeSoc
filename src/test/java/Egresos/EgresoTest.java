package Egresos;

import Seguridad.Contrasenia;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EgresoTest {
    private Egreso egreso;
    private Proveedor proveedor;
    MedioDePago medioDePago;
    List<Item> items = new ArrayList<>();

    public Item unItem;
    public Item otroItem;

    @Before
    public void InicializarTest() {
        proveedor = new Proveedor("juan", 42698536, "almagro");
        medioDePago = new TarjetaCredito(22345);

        unItem = new Item("item1", 100, new Producto());
        otroItem = new Item("item2", 250, new Servicio());

        items.add(unItem);
        items.add(otroItem);

        egreso = new Egreso(proveedor, LocalDate.now(),  medioDePago, items);
    }

     @Test
    public void elValorTotalEs350() {
        Assert.assertEquals(350, egreso.valorTotal());
     }

     // no es necesario este test, pero queriamos saber si la validacionera correcta
     @Test
    public void elProveedorDelEgresoNoPuedeSerNulo(){
         Assert.assertThrows(NullPointerException.class, () -> new Egreso(null, LocalDate.now(),  medioDePago, items ));
     }
}