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

        Spark.before((req, res) -> PerThreadEntityManagers.getEntityManager());
        Spark.after((req, res) -> PerThreadEntityManagers.closeEntityManager());

        Spark.get("/", HomeController::home, engine);
    }
}
