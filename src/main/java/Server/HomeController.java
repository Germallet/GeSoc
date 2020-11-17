package Server;

import spark.*;
import java.util.*;

public class HomeController implements ControllerConUsuario {
    public ModelAndView show(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("inicio", true);
        model.put("usuario", obtenerUsuario(req));
        return new ModelAndView(model, "index.hbs");
    }
}
