package Server;

import Server.Controllers.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import Server.utils.*;

public class Router {
    public static void configure() {
        HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();

        Spark.staticFiles.location("/public");

        Spark.before((req, res) -> PerThreadEntityManagers.getEntityManager());
        Spark.after((req, res) -> PerThreadEntityManagers.closeEntityManager());

        HomeController homeController = new HomeController();
        Spark.get("/", homeController::show, engine);

        LoginController loginController = new LoginController();
        Spark.get("/login", loginController::show, engine);
        Spark.post("/login", loginController::login, engine);

        Spark.post("/logout", LogoutController::logout, engine);

        SignUpController signUpController = new SignUpController();
        Spark.get("/signup", signUpController::show, engine);
        Spark.post("/signup", signUpController::signUp, engine);

        AboutController aboutController = new AboutController();
        Spark.get("/about", aboutController::show, engine);

        EntidadesController entidadesController = new EntidadesController();
        EntidadBaseController entidadBaseController = new EntidadBaseController();
        EntidadJuridicaController entidadJuridicaController = new EntidadJuridicaController();
        Spark.get("/entidades", entidadesController::listar, engine);
        Spark.get("/entidades/:id", entidadesController::mostrar, engine);
        Spark.post("/entidades/:id/base", entidadBaseController::guardar, engine);
        Spark.post("/entidades/:id/juridica", entidadJuridicaController::guardar, engine);

        CategoriasController categoriasController = new CategoriasController();
        Spark.get("/categorias", categoriasController::listar, engine);
        Spark.post("/categorias/new", categoriasController::crear, engine);
        Spark.post("/categorias/:id", categoriasController::editar, engine);
        Spark.post("/categorias/:id/delete", categoriasController::borrar, engine);

        EgresosController egresosController = new EgresosController();
        Spark.get("/entidades/:idEntidad/egresos", egresosController::listar, engine);
        Spark.get("/entidades/:idEntidad/egresos/new", egresosController::nuevo, engine);
        Spark.post("/entidades/:idEntidad/egresos/new", egresosController::crear, engine);
        Spark.get("/entidades/:idEntidad/egresos/:idEgreso", egresosController::mostrar, engine);
        Spark.post("/entidades/:idEntidad/egresos/:idEgreso", egresosController::guardar, engine);
    }
}
