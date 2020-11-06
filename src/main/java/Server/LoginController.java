package Server;

import Main.RepoUsuarios;
import Seguridad.Usuario;
import spark.*;
import java.util.Optional;

public class LoginController {
    public static ModelAndView show(Request req, Response res) {
        if (req.session().attribute("usuario") == null)
            return new ModelAndView(null, "logIn.hbs");
        else {
            res.redirect("/");
            return null;
        }
    }

    public static ModelAndView login(Request req, Response res) {
        String username = req.queryParams("username");
        String password = req.queryParams("password");

        Optional<Usuario> usuario = RepoUsuarios.repositorio().obtenerUsuarios().stream().filter(u -> u.validarLogin(username, password)).findAny();
        if (usuario.isPresent())
        {
            req.session().attribute("usuario", usuario.get());
            res.redirect("/");
        }
        else
            res.redirect("/no");

        return null;
    }
}
