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
    DocumentoComercial documento;
    Egreso egreso;
    Presupuesto presupuestoA;
    Presupuesto presupuestoB;

    @Before
    public void inicializarTest() {
        DocumentoComercial documento= new DocumentoComercial(TipoDeDocumentoComercial.FACTURA);
        Proveedor proveedor = new Proveedor("juan", new Identificador(0, DNI), Mockito.mock(DireccionPostal.class));

        Item itemA = new Item("itemA", 100, TipoDeItem.PRODUCTO);
        Item itemB = new Item("itemB", 200, TipoDeItem.SERVICIO);
        Item itemC = new Item("itemC", 300, TipoDeItem.PRODUCTO);

        List<Item> itemsA = new ArrayList<>();
        itemsA.add(itemA);
        List<Item> itemsB = new ArrayList<>();
        itemsB.add(itemB);
        itemsB.add(itemC);

        presupuestoA = new Presupuesto(proveedor, itemsA, documento);
        presupuestoB = new Presupuesto(proveedor, itemsB, documento);
    }

    @Test
    public void laCantidadDePresupuestosEsMenorQueLosRequeridos() {
        egreso = new Egreso(documento, LocalDate.now(), MedioDePago.TarjetaCredito, 2, false);
        egreso.agregarPresupuesto(presupuestoA);
        egreso.elegirPresupuesto(presupuestoA);

        Assert.assertEquals(false, egreso.esValido());
    }

    @Test
    public void laCantidadDePresupuestosEsMayorQueLosRequeridos() {
        egreso = new Egreso(documento, LocalDate.now(), MedioDePago.TarjetaCredito, 1, false);
        egreso.agregarPresupuesto(presupuestoA);
        egreso.agregarPresupuesto(presupuestoB);
        egreso.elegirPresupuesto(presupuestoA);

        Assert.assertEquals(true, egreso.esValido());
    }

    @Test
    public void presupuestoMenorEnEscogerMenor() {
        egreso = new Egreso(documento, LocalDate.now(), MedioDePago.TarjetaCredito, 0, true);
        egreso.agregarPresupuesto(presupuestoA);
        egreso.agregarPresupuesto(presupuestoB);
        egreso.elegirPresupuesto(presupuestoA);

        Assert.assertEquals(true, egreso.esValido());
    }

    @Test
    public void presupuestoMayorEnEscogerMenor() {
        egreso = new Egreso(documento, LocalDate.now(), MedioDePago.TarjetaCredito, 0, true);
        egreso.agregarPresupuesto(presupuestoA);
        egreso.agregarPresupuesto(presupuestoB);
        egreso.elegirPresupuesto(presupuestoB);

        Assert.assertEquals(false, egreso.esValido());
    }
}