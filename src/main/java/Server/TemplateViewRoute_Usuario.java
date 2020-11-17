package Server;

import Seguridad.Usuario;
import spark.*;

public interface TemplateViewRoute_Usuario {
    ModelAndView handle(Request var1, Response var2, Usuario usuario) throws Exception;
}
