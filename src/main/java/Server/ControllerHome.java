package Server;

import spark.Request;
import spark.Response;

public class ControllerHome {

    public static Object index(Request request, Response response) {
        return "<html> <body> hola ";
    }
}
