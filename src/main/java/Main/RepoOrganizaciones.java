package Main;

import Organizaciones.Organizacion;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoOrganizaciones implements WithGlobalEntityManager {

    private static RepoOrganizaciones instancia = null;

    private RepoOrganizaciones() { }

    public static RepoOrganizaciones repositorio() {
        if (instancia == null)
            instancia = new RepoOrganizaciones();
        return instancia;
    }

    EntityManager em = PerThreadEntityManagers.getEntityManager();

    public void agregarOrganizacion(Organizacion nuevaOrganizacion) {
        em.persist(nuevaOrganizacion);
    }

    public void quitarOrganizacion(Organizacion organizacion) {
        entityManager().remove(organizacion);
    }

    public List<Organizacion> obtenerOrganizaciones(){
        return entityManager().createQuery("from Organizacion").getResultList();
    }
}
