package Server.Controllers;

import Server.ControllerConUsuario;
import spark.*;
import java.util.*;

public class HomeController implements ControllerConUsuario {
    public static ModelAndView show(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("inicio", true);
        model.put("usuario", ControllerConUsuario.obtenerUsuario(req));
        return new ModelAndView(model, "index.hbs");
    }
}
