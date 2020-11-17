package Server.Controllers;

import spark.*;

public class LogoutController {
    public static ModelAndView logout(Request req, Response res) {
        req.session().removeAttribute("usuario");
        res.redirect("/");
        return null;
    }
}
