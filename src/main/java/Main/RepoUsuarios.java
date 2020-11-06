package Main;

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

    public void agregarUsuario(Usuario nuevoUsuario) { entityManager().persist(nuevoUsuario); }

    public void quitarUsuario(Usuario usuario) {
        entityManager().remove(usuario);
    }

    public List<Usuario> obtenerUsuarios(){
        return entityManager().createQuery("from Usuario", Usuario.class).getResultList();
    }
}
