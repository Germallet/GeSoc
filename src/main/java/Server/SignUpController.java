package Server;

import Main.RepoUsuarios;
import Seguridad.*;
import Seguridad.ValidadorDeContrasenia.*;
import org.uqbarproject.jpa.java8.extras.*;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.*;

import java.util.*;

public class SignUpController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
    public ModelAndView show(Request req, Response res){
        return new ModelAndView(null, "signup.hbs");
    }

    public ModelAndView signUp(Request req, Response res) throws PasswordException {
        String username = req.queryParams("username");
        String password = req.queryParams("password");

        Contrasenia contrasenia;
        contrasenia = new Contrasenia(password,
                Arrays.asList(new ValidadorDeContrasenia_Longitud(5),
                              new ValidadorDeContrasenia_NoEnDiccionario("10k-most-common.txt"),
                              new ValidadorDeContrasenia_TieneCaracterEspecial()));

        if (RepoUsuarios.repositorio().obtenerUsuarios().stream().anyMatch(u -> u.getNombre().equals(username)))
        {
            Map<String, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("usernameError", "Nombre de usuario en uso");
            return new ModelAndView(model, "signup.hbs");
        }

        Usuario nuevoUsuario = new Usuario(TipoDeUsuario.ESTANDAR, username, contrasenia);
        withTransaction(() -> RepoUsuarios.repositorio().agregarUsuario(nuevoUsuario));
        res.redirect("/login");
        return null;
    }
}
