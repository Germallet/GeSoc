package Server;

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

        Spark.get("/", HomeController::show, engine);

        Spark.get("/login", LoginController::show, engine);
        Spark.post("/login", LoginController::login, engine);

        Spark.post("/logout", LogoutController::logout, engine);

        SignUpController signUpController = new SignUpController();
        Spark.get("/signup", signUpController::show, engine);
        Spark.post("/signup", signUpController::signUp, engine);

        Spark.get("/about", AboutController::show, engine);

        EntidadesController entidadesController = new EntidadesController();
        Spark.get("/entidades", entidadesController::listar, engine);
        Spark.get("/entidades/:id", entidadesController::mostrar, engine);
        Spark.post("/entidades/:id", entidadesController::guardar, engine);

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
