package db;

import Main.RepoOrganizaciones;
import Organizaciones.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
    Organizacion organizacion;

    @Before
    public void inicializarTest(){
        organizacion = new Organizacion(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        entityManager().clear();
    }

    @Test
    public void agregoUnaOrganzacion() {
        EntityTransaction transaction = entityManager().getTransaction();
        RepoOrganizaciones.repositorio().agregarOrganizacion(organizacion);
        transaction.commit();

        List<Organizacion> organizaciones = RepoOrganizaciones.repositorio().obtenerOrganizaciones();
        Assert.assertTrue(organizaciones.contains(organizacion));
    }

    @Test
    public void obtengoLasOrganizaciones() {
        List<Organizacion> organizaciones = RepoOrganizaciones.repositorio().obtenerOrganizaciones();
        Assert.assertTrue(organizaciones.size() != 0);
    }

    @Test
    public void borroUnaOrganizacion() {
        EntityTransaction transaction = entityManager().getTransaction();
        RepoOrganizaciones.repositorio().quitarOrganizacion(organizacion);
        transaction.commit();

        List<Organizacion> organizaciones = RepoOrganizaciones.repositorio().obtenerOrganizaciones();
        Assert.assertTrue(!organizaciones.contains(organizacion));
    }
}
