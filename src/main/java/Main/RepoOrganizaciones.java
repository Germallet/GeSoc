package Main;

import Organizaciones.Organizacion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import java.util.List;

public class RepoOrganizaciones implements WithGlobalEntityManager {

    private static RepoOrganizaciones instancia = null;

    private RepoOrganizaciones() { }

    public static RepoOrganizaciones repositorio() {
        if (instancia == null)
            instancia = new RepoOrganizaciones();
        return instancia;
    }

    public void agregarOrganizacion(Organizacion nuevaOrganizacion) {
        entityManager().persist(nuevaOrganizacion);
    }

    public void quitarOrganizacion(Organizacion organizacion) {
        entityManager().remove(organizacion);
    }

    public List<Organizacion> obtenerOrganizaciones(){
        return entityManager().createQuery("from Organizacion", Organizacion.class).getResultList();
    }
}
