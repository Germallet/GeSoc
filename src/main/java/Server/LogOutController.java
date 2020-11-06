package Server;

import spark.*;

public class LogOutController {
    public static ModelAndView logOut(Request req, Response res) {
        req.session().removeAttribute("usuario");
        res.redirect("/");
        return null;
    }
}
