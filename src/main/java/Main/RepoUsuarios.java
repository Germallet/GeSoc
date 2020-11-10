package Main;

import Organizaciones.*;
import Seguridad.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import java.util.List;

public class RepoUsuarios implements WithGlobalEntityManager {

    private static RepoUsuarios instancia = null;

    private RepoUsuarios() { }

    public static RepoUsuarios repositorio() {
        if (instancia == null)
            instancia = new RepoUsuarios();
        return instancia;
    }

    public void agregarUsuario(Usuario nuevoUsuario) {
        entityManager().persist(nuevoUsuario.getOrganizacion());
        entityManager().persist(nuevoUsuario);

        nuevoUsuario.getOrganizacion().getEntidades().add(new Base("Nombre Ficticio A", "Descripción A", null));
        nuevoUsuario.getOrganizacion().getEntidades().add(new Juridica("Razón Social A", "Nombre Ficticio A", 1, 2,  null));
        nuevoUsuario.getOrganizacion().getEntidades().add(new Base("Nombre Ficticio B", "Descripción B", null));
        nuevoUsuario.getOrganizacion().getEntidades().add(new Juridica("Razón Social B", "Nombre Ficticio B", 3, 4, null));
        nuevoUsuario.getOrganizacion().getEntidades().forEach(entidad -> entityManager().persist(entidad));
        entityManager().persist(nuevoUsuario);
    }

    public void quitarUsuario(Usuario usuario) {
        entityManager().remove(usuario);
    }

    public List<Usuario> obtenerUsuarios(){
        return entityManager().createQuery("from Usuario", Usuario.class).getResultList();
    }
}
