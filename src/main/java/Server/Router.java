package Server;

import Seguridad.Usuario;
import Server.Controllers.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.*;
import spark.template.handlebars.*;
import Server.utils.*;

public class Router implements ControllerConUsuario {
    public static void configure() {
        HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();

        //Spark.staticFiles.location("/public");

        Spark.before((req, res) -> PerThreadEntityManagers.getEntityManager());
        Spark.after((req, res) -> PerThreadEntityManagers.closeEntityManager());

        Spark.get("/", HomeController::show, engine);

        Spark.get("/login", LoginController::show, engine);
        Spark.post("/login", LoginController::login, engine);

        Spark.post("/logout", LogoutController::logout, engine);

        SignUpController signUpController = new SignUpController();
        Spark.get("/signup", signUpController::show, engine);
        Spark.post("/signup", signUpController::signUp, engine);

        Spark.get("/about", AboutController::show, engine);

        EntidadesController entidadesController = new EntidadesController();
        EntidadBaseController entidadBaseController = new EntidadBaseController();
        EntidadJuridicaController entidadJuridicaController = new EntidadJuridicaController();
        get_LoginRequerido("/entidades", entidadesController::listar, engine);
        get_LoginRequerido("/entidades/:idEntidad", entidadesController::mostrar, engine);
        post_LoginRequerido("/entidades/:idEntidad/base", entidadBaseController::guardar, engine);
        post_LoginRequerido("/entidades/:idEntidad/juridica", entidadJuridicaController::guardar, engine);

        CategoriasController categoriasController = new CategoriasController();
        get_LoginRequerido("/categorias", categoriasController::listar, engine);
        post_LoginRequerido("/categorias/new", categoriasController::crear, engine);
        post_LoginRequerido("/categorias/:id", categoriasController::editar, engine);
        post_LoginRequerido("/categorias/:id/delete", categoriasController::borrar, engine);

        EgresosController egresosController = new EgresosController();
        get_LoginRequerido("/entidades/:idEntidad/egresos", egresosController.ToViewRoute(egresosController::listar), engine);
        get_LoginRequerido("/entidades/:idEntidad/egresos/new", egresosController.ToViewRoute(egresosController::nuevo), engine);
        post_LoginRequerido("/entidades/:idEntidad/egresos/new", egresosController.ToViewRoute(egresosController::crear), engine);
        get_LoginRequerido("/entidades/:idEntidad/egresos/:idEgreso", egresosController.ToViewRoute(egresosController::mostrar), engine);
        post_LoginRequerido("/entidades/:idEntidad/egresos/:idEgreso", egresosController.ToViewRoute(egresosController::guardar), engine);
    }

    private static void get_LoginRequerido(String path, TemplateViewRoute_Usuario route, TemplateEngine engine) {
        Spark.get(path, loginRequerido(route), engine);
    }
    private static void post_LoginRequerido(String path, TemplateViewRoute_Usuario route, TemplateEngine engine) {
        Spark.post(path, loginRequerido(route), engine);
    }

    private static TemplateViewRoute loginRequerido(TemplateViewRoute_Usuario route) {
        return (Request req, Response res) -> {
            Usuario usuario = ControllerConUsuario.obtenerUsuario(req);
            if (usuario == null) {
                res.redirect("/login");
                return null;
            } else
                return route.handle(req, res, usuario);
        };
    }
}
