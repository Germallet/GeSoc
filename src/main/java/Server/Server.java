package Server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
    public static void main(String[] args) {
        Bootstrap.init();
        Spark.port(9000);
        DebugScreen.enableDebugScreen(); // para el debug web
        Spark.init();
        Router.configure();
        Spark.get("/", (request, response) -> {
            return new OrganizacionController().get(request, response);
        });
    }
}
