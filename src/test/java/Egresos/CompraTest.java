package Egresos;
import Egresos.*;
import Organizaciones.Organizacion;
import Proveedor.Identificador;
import Proveedor.Proveedor;
import Egresos.Presupuesto;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Or;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Egresos.DocumentoComercial;

import static Proveedor.TipoDeID.CUIT;
import static Proveedor.TipoDeID.DNI;

public class CompraTest {

    boolean requierePresupuestos;
    int presupuestosRequeridos;
    List<Presupuesto> presupuestos;
    Egreso egreso;

    @Before
    public void inicializarTest() {

        Organizacion organizacion = new Organizacion();
        DocumentoComercial documento = new DocumentoComercial(12, TipoDeDocumentoComercial.BOLETA);
        Proveedor proveedor = new Proveedor("Pablo perez", new Identificador(123, CUIT), "Mozart 3200");
        LocalDate fecha = LocalDate.now();
        MedioDePago tarjetaCredito = new TarjetaCredito(1243);
        List<Item> items = new ArrayList<>();

        presupuestos = new ArrayList<>();
        int cantidadPresupuestoRequerido;

        Item item0 = new Item("item0", 124, TipoDeItem.PRODUCTO);
        Item item1 = new Item("item0", 125, TipoDeItem.PRODUCTO);
        Item item2 = new Item("item0", 125, TipoDeItem.PRODUCTO);

        items.add(item0);
        items.add(item1);
        items.add(item2);

        DocumentoComercial docComercial1 = new DocumentoComercial(123, TipoDeDocumentoComercial.BOLETA);
        DocumentoComercial docComercial2 = new DocumentoComercial(1512, TipoDeDocumentoComercial.CHEQUES);

        List<DocumentoComercial> documentos = new ArrayList<>();
        documentos.add(docComercial1);
        documentos.add(docComercial2);

        egreso = new Egreso(organizacion, proveedor, fecha, tarjetaCredito, items, 3);

        requierePresupuestos = true;
        presupuestosRequeridos = 3;

        Presupuesto unPresupuesto = new Presupuesto("Presupuesto numero 1", egreso, documentos, 2134);
        Presupuesto otroPresupuesto = new Presupuesto("Presupuesto numero 2", egreso, documentos, 51254);

        presupuestos.add(unPresupuesto);
        presupuestos.add(otroPresupuesto);
        presupuestos.add(otroPresupuesto);

    }

    @Test
    public void laCantidadDePresupuestosCargadosEsIgualQueLosRequeridos() {
        Assert.assertEquals(presupuestos.size(), presupuestosRequeridos);
    }

    /*
    @Test
    public void laCompraFueRealizadaEnBaseAUnPresupuestoCargado() {
        Assert.assertTrue(presupuestos.stream().anyMatch(egreso.presupuestos.get(1)));
    }
    */


}