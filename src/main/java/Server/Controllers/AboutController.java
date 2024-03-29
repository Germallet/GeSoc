package Server.Controllers;

import Server.ControllerConUsuario;
import spark.*;
import java.util.*;

public class AboutController implements ControllerConUsuario {
    public static ModelAndView show(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("about", true);
        model.put("usuario", ControllerConUsuario.obtenerUsuario(req));
        return new ModelAndView(model, "about.hbs");
    }
}
