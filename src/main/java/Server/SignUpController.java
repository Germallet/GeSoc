package Server;

import Main.RepoUsuarios;
import Seguridad.*;
import org.uqbarproject.jpa.java8.extras.*;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.*;
import java.util.ArrayList;

public class SignUpController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
    public ModelAndView show(Request req, Response res){
        return new ModelAndView(null, "signUp.hbs");
    }

    public ModelAndView signUp(Request req, Response res) {
        String username = req.queryParams("username");
        String password = req.queryParams("password");

        Usuario nuevoUsuario = new Usuario(TipoDeUsuario.ESTANDAR, username, new Contrasenia(password, new ArrayList<>()));
        withTransaction(() -> RepoUsuarios.repositorio().agregarUsuario(nuevoUsuario));

        res.redirect("/");

        return null;
    }
}
