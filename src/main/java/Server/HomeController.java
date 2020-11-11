package Server;

import spark.*;
import java.util.*;

public class HomeController {
    public static ModelAndView show(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("inicio", true);
        model.put("usuario", req.session().attribute("usuario"));
        return new ModelAndView(model, "index.hbs");
    }
}
