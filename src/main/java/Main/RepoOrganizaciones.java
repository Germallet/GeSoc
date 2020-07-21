package Main;

import Organizaciones.Organizacion;
import java.util.ArrayList;
import java.util.List;

public class RepoOrganizaciones {
    private static RepoOrganizaciones instancia = null;
    private RepoOrganizaciones() {
        this.instancia = new RepoOrganizaciones();
    }

    public static RepoOrganizaciones repositorio() {
        if (instancia == null)
            instancia = new RepoOrganizaciones();
        return instancia;
    }

    private List<Organizacion> organizaciones = new ArrayList<>();

    public void agregar(Organizacion nuevaOrganizacion) {
        organizaciones.add(nuevaOrganizacion);
    }

    public void quitar(Organizacion nuevaOrganizacion) {
        organizaciones.remove(nuevaOrganizacion);
    }

    public List<Organizacion> obtener() {
        return organizaciones;
    }
}