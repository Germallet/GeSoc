package Server;

import Main.RepoOrganizaciones;
import Organizaciones.*;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class OrganizacionController {
    public String get(Request request, Response response) {
        List<Organizacion> organizaciones = RepoOrganizaciones.repositorio().obtenerOrganizaciones();
        Organizacion org = organizaciones.get(0); // no hay organizaciones en la database x defaultre
        response.type("application/json"); // cambia el tipo de form
        return new Gson().toJson(org);
    }
}
