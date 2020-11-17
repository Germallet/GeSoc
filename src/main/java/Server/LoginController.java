package Server;

import Main.RepoUsuarios;
import Seguridad.Usuario;
import spark.*;
import java.util.*;

public class LoginController implements ControllerConUsuario {
    public ModelAndView show(Request req, Response res) {
        if (!estaLogueado(req))
            return new ModelAndView(null, "logIn.hbs");
        else {
            res.redirect("/");
            return null;
        }
    }

    public ModelAndView login(Request req, Response res) {
        String username = req.queryParams("username");
        String password = req.queryParams("password");

        Optional<Usuario> usuario = RepoUsuarios.repositorio().obtenerUsuarios().stream().filter(u -> u.validarLogin(username, password)).findAny();
        if (usuario.isPresent())
        {
            req.session().attribute("usuario", usuario.get().getId());
            res.redirect("/");
        }
        else
        {
            Map<String, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("loginError", true);
            return new ModelAndView(model, "login.hbs");
        }

        return null;
    }
}
