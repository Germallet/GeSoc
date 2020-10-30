package Server;


import spark.Spark;

public class Router { //un router configura todas las ruutas

    public static void configure() {
            Spark.get("/", ControllerHome::index);
    }

}