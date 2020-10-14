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

    @Test
    public void agregoUnaOrganzacion() {
        Organizacion organizacion = new Organizacion(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        RepoOrganizaciones.repositorio().agregarOrganizacion(organizacion);

        List<Organizacion> organizaciones = RepoOrganizaciones.repositorio().obtenerOrganizaciones();
        Assert.assertTrue(organizaciones.contains(organizacion));
    }

    @Test
    public void obtengoLasOrganizaciones() {
        Organizacion organizacion = new Organizacion(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        RepoOrganizaciones.repositorio().agregarOrganizacion(organizacion);

        List<Organizacion> organizaciones = RepoOrganizaciones.repositorio().obtenerOrganizaciones();
        Assert.assertTrue(organizaciones.size() == 1);
        Assert.assertTrue(organizaciones.stream().findFirst().get() == organizacion);
    }

    @Test
    public void borroUnaOrganizacion() {
        Organizacion organizacion = new Organizacion(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        RepoOrganizaciones.repositorio().agregarOrganizacion(organizacion);
        RepoOrganizaciones.repositorio().quitarOrganizacion(organizacion);

        List<Organizacion> organizaciones = RepoOrganizaciones.repositorio().obtenerOrganizaciones();
        Assert.assertTrue(organizaciones.isEmpty());
    }
}
