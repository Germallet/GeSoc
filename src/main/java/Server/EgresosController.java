package Server;

import Main.RepoEgresos;
import org.hibernate.metamodel.source.annotations.xml.mocker.MockHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import Egresos.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import Main.RepoEgresos;

import javax.jws.WebParam;

import static Main.RepoEgresos.repositorio;

public class EgresosController {

    public ModelAndView listar(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        List<Egreso> egresos = repositorio().obtenerEgresos();
        model.put("egresos", egresos);
        return new ModelAndView(model, "egresos/index.hbs");
    }

    public ModelAndView mostrar(Request req, Response res) {
        Map<String, Egreso> model = new HashMap<>();
        Egreso egreso = new Egreso() ;//= RepoEgresos.repositorio().obtenerEgreso(id);
        model.put("egreso", egreso);
        return new ModelAndView(null, "egresos/show.hbs");
    }

    public ModelAndView nuevo(Request request, Response response) {
        return new ModelAndView(null, "egresos/new.hbs");
    }

    public Void crear(Request req, Response res) {
        Egreso egreso = new Egreso();
        Map<String, Object> model = new HashMap<>();
        model.put("nombre egreso", req.queryParams("nombre"));
        model.put("id", req.queryParams("id"));
        RepoEgresos.repositorio().agregarEgreso(egreso);
        res.redirect("/egresos");
        return null;
    }

}


