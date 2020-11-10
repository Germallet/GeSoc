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

        Spark.get("/logout", LogoutController::logout, engine);

        SignUpController signUpController = new SignUpController();
        Spark.get("/signup", signUpController::show, engine);
        Spark.post("/signup", signUpController::signUp, engine);

        Spark.get("/about", AboutController::show, engine);

<<<<<<< HEAD
        EgresosController egresosController = new EgresosController();
        Spark.get("/egresos", egresosController::listar, engine);
        Spark.get("/egresos/new", egresosController::nuevo, engine);
        Spark.get("/egresos/:id", egresosController::mostrar, engine);
        Spark.post("/egresos", egresosController::crear);
=======
        Spark.get("/entidades", EntidadesController::show, engine);

        Spark.get("/egresos", EgresosController::show, engine);
        //Spark.post("/egresos/add", EgresosController::add, engine);
>>>>>>> e8fb21fcbb346342abbcd663ede25222117aaa6c
    }
}
