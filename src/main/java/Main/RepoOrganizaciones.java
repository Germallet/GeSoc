package Main;

import Egresos.Egreso;
import Organizaciones.Organizacion;
import Proveedor.Proveedor;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class RepoOrganizaciones implements WithGlobalEntityManager {

    private static RepoOrganizaciones instancia = null;

    private List<Organizacion> organizaciones = new ArrayList<>();

    public static RepoOrganizaciones repositorio() {
        if (instancia == null)
            instancia = new RepoOrganizaciones();
        return instancia;
    }

    EntityManager em = PerThreadEntityManagers.getEntityManager();
    EntityTransaction transaction = em.getTransaction();

  //  private RepoOrganizaciones() { this.instancia = new RepoOrganizaciones();} tira stackOverflow cuando interactua con la db

    public void agregarOrganizacion(Organizacion nuevaOrganizacion) {
        transaction.begin();
        em.persist(nuevaOrganizacion); // se guarda en la base de datos
        transaction.commit();
    }

    public void agregarEgreso(Egreso egreso){
        transaction.begin();
        em.persist(egreso);
        transaction.commit();
    }

    public void agregarProveedor(Proveedor prov){
        transaction.begin();
        em.persist(prov);
        transaction.commit();
    }

    public void quitarOrganizacion(long id_org) {
        transaction.begin();
        Organizacion org = entityManager().find(Organizacion.class, new Long(id_org));
        entityManager().remove(org);
        transaction.commit();
    }

    public List<Organizacion> obtenerOrganizaciones(){
        return entityManager()
                .createQuery("from Organizacion").getResultList();
    }
}
