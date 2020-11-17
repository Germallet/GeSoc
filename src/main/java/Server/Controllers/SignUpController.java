package Server.Controllers;

import Main.RepoUsuarios;
import Seguridad.Contrasenia;
import Seguridad.TipoDeUsuario;
import Seguridad.Usuario;
import Seguridad.ValidadorDeContrasenia.PasswordException;
import Seguridad.ValidadorDeContrasenia.ValidadorDeContrasenia_Longitud;
import Seguridad.ValidadorDeContrasenia.ValidadorDeContrasenia_NoEnDiccionario;
import Seguridad.ValidadorDeContrasenia.ValidadorDeContrasenia_TieneCaracterEspecial;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SignUpController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
    public ModelAndView show(Request req, Response res) {
        return new ModelAndView(null, "signup.hbs");
    }

    public ModelAndView signUp(Request req, Response res) {
        String username = req.queryParams("username");
        String password = req.queryParams("password");

        Contrasenia contrasenia;
        try {
            contrasenia = new Contrasenia(password, Arrays.asList(new ValidadorDeContrasenia_Longitud(5), new ValidadorDeContrasenia_NoEnDiccionario("10k-most-common.txt"), new ValidadorDeContrasenia_TieneCaracterEspecial()));
        } catch (PasswordException e) {
            Map<String, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("passwordError", e.getMessage());
            return new ModelAndView(model, "signup.hbs");
        }

        if (RepoUsuarios.repositorio().obtenerUsuarios().stream().anyMatch(u -> u.getNombre().equals(username))) {
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
