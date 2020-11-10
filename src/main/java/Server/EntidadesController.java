package Server;

import Organizaciones.Entidad;
import Seguridad.*;
import spark.*;
import java.util.*;

public class EntidadesController {
    public static ModelAndView show(Request req, Response res) {
        Usuario usuario = req.session().attribute("usuario");
        if (usuario == null) {
            res.redirect("/login");
            return null;
        }

        Map<String, Object> model = new HashMap<>();
        model.put("usuario", usuario);
        model.put("entidades", usuario.getOrganizacion().getEntidades());
        return new ModelAndView(model, "entidades.hbs");
    }
}
