package Egresos;

import Organizaciones.Etiqueta;
import org.junit.*;
import java.util.*;
import java.time.LocalDate;

public class ReporteTest {

    @Test
    public void elReporteEsDelUltimoMes() {
        LocalDate now = LocalDate.now();

        Egreso egreso1 = new Egreso(null, LocalDate.of(now.getYear(), now.getMonth(), 1), MedioDePago.TarjetaCredito, 0, false);
        Egreso egreso2 = new Egreso(null, LocalDate.of(now.getYear(), now.getMonth(), 2), MedioDePago.TarjetaCredito, 0, false);
        Egreso egreso3 = new Egreso(null, LocalDate.of(now.getYear(), now.getMonth().minus(1), 10), MedioDePago.TarjetaCredito, 0, false);
        Egreso egreso4 = new Egreso(null, LocalDate.of(now.getYear()-1, now.getMonth(), 10), MedioDePago.TarjetaCredito, 0, false);
        Egreso egreso5 = new Egreso(null, LocalDate.of(now.getYear()-1, now.getMonth().minus(1), 10), MedioDePago.TarjetaCredito, 0, false);

        ArrayList<Egreso> egresos = new ArrayList<>();
        egresos.add(egreso1);
        egresos.add(egreso2);
        egresos.add(egreso3);
        egresos.add(egreso4);
        egresos.add(egreso5);

        Reporte reporte = new Reporte(egresos);
        Assert.assertTrue(reporte.egresos().contains(egreso1));
        Assert.assertTrue(reporte.egresos().contains(egreso2));
        Assert.assertTrue(!reporte.egresos().contains(egreso3));
        Assert.assertTrue(!reporte.egresos().contains(egreso4));
        Assert.assertTrue(!reporte.egresos().contains(egreso5));
    }

    @Test
    public void elReporteEsPorEtiqueta() {
        Etiqueta etiqueta1 = new Etiqueta("etiqueta1");
        Etiqueta etiqueta2 = new Etiqueta("etiqueta2");

        Egreso egreso1 = new Egreso(null, LocalDate.now(), MedioDePago.TarjetaCredito, 0, false);
        egreso1.agregarEtiqueta(etiqueta1);
        Egreso egreso2 = new Egreso(null, LocalDate.now(), MedioDePago.TarjetaCredito, 0, false);
        egreso2.agregarEtiqueta(etiqueta1);
        Egreso egreso3 = new Egreso(null, LocalDate.now(), MedioDePago.TarjetaCredito, 0, false);
        egreso3.agregarEtiqueta(etiqueta2);
        Egreso egreso4 = new Egreso(null, LocalDate.now(), MedioDePago.TarjetaCredito, 0, false);

        ArrayList<Egreso> egresos = new ArrayList<>();
        egresos.add(egreso1);
        egresos.add(egreso2);
        egresos.add(egreso3);
        egresos.add(egreso4);

        Reporte reporte = new Reporte(egresos);
        List<Egreso> egresosReporte = reporte.generarEgresosPorEtiqueta(etiqueta1);
        Assert.assertTrue(egresosReporte.contains(egreso1));
        Assert.assertTrue(egresosReporte.contains(egreso2));
        Assert.assertTrue(!egresosReporte.contains(egreso3));
        Assert.assertTrue(!egresosReporte.contains(egreso4));
    }
}
