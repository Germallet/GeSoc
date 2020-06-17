package Egresos;
import Egresos.*;
import Localizacion.DireccionPostal;
import Organizaciones.Organizacion;
import Proveedor.Identificador;
import Proveedor.Proveedor;
import Egresos.Presupuesto;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Or;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Egresos.DocumentoComercial;

import static Proveedor.TipoDeID.CUIT;
import static Proveedor.TipoDeID.DNI;

public class CompraPendienteTest {

    int presupuestosRequeridos;
    List<Presupuesto> presupuestos;
    Egreso egreso;
    CompraPendiente compraP;
    boolean escogerMenor;

    @Before
    public void inicializarTest() {

        Organizacion organizacion = new Organizacion();
        DocumentoComercial documento = new DocumentoComercial(12, TipoDeDocumentoComercial.BOLETA);
        Proveedor proveedor = new Proveedor("Pablo perez", new Identificador(123, CUIT), Mockito.mock(DireccionPostal.class));
        LocalDate fecha = LocalDate.now();
        MedioDePago tarjetaCredito = new TarjetaCredito(1243);
        List<Item> items = new ArrayList<>();


        presupuestos = new ArrayList<>();

        Item item0 = new Item("item0", 124, TipoDeItem.PRODUCTO);
        Item item1 = new Item("item0", 125, TipoDeItem.PRODUCTO);
        Item item2 = new Item("item0", 125, TipoDeItem.PRODUCTO);

        items.add(item0);
        items.add(item1);
        items.add(item2);

        DocumentoComercial docComercial1 = new DocumentoComercial(123, TipoDeDocumentoComercial.BOLETA);
        DocumentoComercial docComercial2 = new DocumentoComercial(1512, TipoDeDocumentoComercial.CHEQUES);



        compraP = new CompraPendiente(organizacion,presupuestosRequeridos,escogerMenor);

        boolean escogerMenor=true;
        presupuestosRequeridos = 3;

        Presupuesto unPresupuesto = new Presupuesto( proveedor, items, egreso, docComercial1);
        Presupuesto otroPresupuesto = new Presupuesto( proveedor, items, egreso, docComercial2);

        presupuestos.add(unPresupuesto);
        presupuestos.add(otroPresupuesto);
        presupuestos.add(otroPresupuesto);

    }
    @Test
    public void laCantidadDePresupuestosCargadosEsIgualQueLosRequeridos() {
        Assert.assertTrue(presupuestos.size() >= presupuestosRequeridos);
    }

}

