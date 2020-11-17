package Server;

import Main.RepoUsuarios;
import Seguridad.Usuario;
import spark.Request;

import java.util.Optional;

public interface ControllerConUsuario {
    default Boolean estaLogueado(Request req) {
        return req.session().attribute("usuario") != null;
    }

    default Usuario obtenerUsuario(Request req) {
        try {
            long id = req.session().attribute("usuario");
            Optional<Usuario> usuario = RepoUsuarios.repositorio().obtenerUsuarios().stream().filter(u -> u.getId() == id).findAny();
            return usuario.orElseGet(null);
        }
        catch (Exception e) {
            return null;
        }
    }
}
