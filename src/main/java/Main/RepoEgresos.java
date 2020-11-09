package Main;

import Egresos.*;
import Seguridad.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import java.util.List;

public class RepoEgresos implements WithGlobalEntityManager {

    private static RepoEgresos instancia = null;

    private RepoEgresos() { }

    public static RepoEgresos repositorio() {
        if (instancia == null)
            instancia = new RepoEgresos();
        return instancia;
    }

    public void agregarEgreso(Egreso nuevoEgreso) { entityManager().persist(nuevoEgreso); }

    public List<Egreso> obtenerEgresos(){
        return entityManager().createQuery("from Egreso", Egreso.class).getResultList();
    }
}
