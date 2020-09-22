package db;
import Egresos.*;
import Localizacion.DireccionPostal;
import Main.RepoOrganizaciones;
import Organizaciones.*;
import Organizaciones.ComportamientoPermitirEgreso.ComportamientoPermitirEgreso;
import Proveedor.Proveedor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Or;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.GeneratedValue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static Proveedor.TipoDeID.DNI;

public class RepositorioTest {
    DocumentoComercial documento;
    List<Egreso> egresos;
    Presupuesto presupuestoA;
    Presupuesto presupuestoB;
    Organizacion org;
    List<Entidad> entidades;
    List<Etiqueta> etiquetas;
    List<Categoria> categorias;

    @Before
    public void inicializarTest(){

        DocumentoComercial documento = new DocumentoComercial(TipoDeDocumentoComercial.FACTURA);

        Egreso egreso1 = new Egreso(documento, LocalDate.now(), MedioDePago.TarjetaCredito ,3,false);
        Egreso egreso2 = new Egreso(documento, LocalDate.now(), MedioDePago.DineroEnCuenta ,2,true);
        egresos = new ArrayList<>();
        egresos.add(egreso1);
        egresos.add(egreso2);

        Categoria categoria1 = new Categoria("unaCategoria", true, false);
        Categoria categoria2 = new Categoria("otraCategoria", false, true);
        //categorias.add(categoria1);
        //categorias.add(categoria2);

        entidades = new ArrayList<>();

        Etiqueta etiqueta1 = new Etiqueta("etiqueta_lacoste");
        Etiqueta etiqueta2 = new Etiqueta("etiqueta_tommy");
        //etiquetas.add(etiqueta1);
        //etiquetas.add(etiqueta2);

        org = new Organizacion(new Long(2),entidades, etiquetas, categorias);
    }

    @Test
    public void agregoUnaOrganzacion() {
        RepoOrganizaciones.repositorio().agregarOrganizacion(org);
        List<Organizacion> organizaciones = RepoOrganizaciones.repositorio().obtenerOrganizaciones();
        Assert.assertTrue(organizaciones.contains(org));
    }


}
