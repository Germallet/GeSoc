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

import static Main.RepoEgresos.repositorio;

public class EgresosController {

    public static ModelAndView show(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        List<Egreso> egresos = repositorio().obtenerEgresos();
        model.put("egresos", egresos);
        return new ModelAndView(model, "egresos.hbs");
    }


    public void add(Request req, Response res) {

        DocumentoComercial doc;
        LocalDate fecha;
        MedioDePago mediopago;
        boolean escogerMenor;
        Egreso egreso = new Egreso();
        Map<String, Object> model = new HashMap<>();
        model.put("nombre egreso", req.queryParams("nombre"));
        model.put("medio de pago", req.queryParams("medio_pago"));
        model.put("fecha", req.queryParams("fecha_egreso"));
        RepoEgresos.repositorio().agregarEgreso(egreso);

    }
}


