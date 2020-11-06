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

        Spark.get("/login", LogInController::show, engine);
        Spark.post("/login", LogInController::logIn, engine);

        Spark.get("/logout", LogOutController::logOut, engine);

        SignUpController signUpController = new SignUpController();
        Spark.get("/signup", signUpController::show, engine);
        Spark.post("/signup", signUpController::signUp, engine);

        Spark.get("/about", AboutController::show, engine);
    }
}
