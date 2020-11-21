package Server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
    public static void main(String[] args) {
        Spark.port(puerto());
        DebugScreen.enableDebugScreen();
        Router.configure();
    }

    private static int puerto() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null)
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        return 80;
    }
}
