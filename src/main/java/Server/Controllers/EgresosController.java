package Server.Controllers;

import Organizaciones.Entidad;
import Persistence.WithGlobalEntityManagerEnv;
import Seguridad.Usuario;
import Server.TemplateViewRoute_Usuario;
import org.uqbarproject.jpa.java8.extras.*;
import org.uqbarproject.jpa.java8.extras.transaction.*;
import spark.*;
import java.time.LocalDate;
import java.util.*;
import Egresos.*;

public class EgresosController implements WithGlobalEntityManagerEnv, EntityManagerOps, TransactionalOps {

    public interface TemplateViewRoute_UsuarioEntidad {
        ModelAndView handle(Request var1, Response var2, Usuario usuario, Entidad entidad) throws Exception;
    }

    public TemplateViewRoute_Usuario ToViewRoute(TemplateViewRoute_UsuarioEntidad funcion) {
        return (Request req, Response res, Usuario usuario) -> {
            Optional<Entidad> entidad = usuario.getEntidadConId(req.params("idEntidad"));
            if(!entidad.isPresent()) {
                res.redirect("/entidades");
                return null;
            }
            return funcion.handle(req, res, usuario, entidad.get());
        };
    }

    private Optional<Egreso> obtenerEgreso(Request req, Entidad entidad) {
        return entidad.getEgresos().stream().filter(e -> Long.parseLong(req.params("idEgreso")) == e.getId()).findAny();
    }

    public ModelAndView listar(Request req, Response res, Usuario usuario, Entidad entidad) {
        Map<String, Object> model = new HashMap<>();
        model.put("entidad", entidad);
        model.put("egresos", entidad.getEgresos());
        model.put("usuario", usuario);
        return new ModelAndView(model, "egresos/listar.hbs");
    }

    public ModelAndView mostrar(Request req, Response res, Usuario usuario, Entidad entidad) {
        Optional<Egreso> egresoOptional = obtenerEgreso(req, entidad);
        if(!egresoOptional.isPresent()) {
            res.redirect("/entidades/" + entidad.getId() + "/egresos");
            return null;
        }
        Egreso egreso = egresoOptional.get();

        String medioDePago;
        switch(egreso.getMedioDePago()) {
            case TarjetaCredito: medioDePago = "tarjetaCredito"; break;
            case TarjetaDebito: medioDePago = "tarjetaDebito"; break;
            case Efectivo: medioDePago = "efectivo"; break;
            case CajeroAutomatico: medioDePago = "cajeroAutomatico"; break;
            case DineroEnCuenta: medioDePago = "dineroEnCuenta"; break;
            default: throw new IllegalArgumentException();
        }

        Map<String, Object> model = new HashMap<>();
        model.put("usuario", usuario);
        model.put("entidad", entidad);
        model.put("egreso", egreso);
        model.put(medioDePago, true);
        return new ModelAndView(model, "egresos/mostrar.hbs");
    }

    public ModelAndView nuevo(Request req, Response res, Usuario usuario, Entidad entidad) {
        Map<String, Object> model = new HashMap<>();
        model.put("usuario", usuario);
        model.put("entidad", entidad);
        return new ModelAndView(model, "egresos/new.hbs");
    }

    public ModelAndView crear(Request req, Response res, Usuario usuario, Entidad entidad) {
        Egreso egreso = new Egreso(null, LocalDate.parse(req.queryParams("fecha")), MedioDePago.valueOf(req.queryParams("medioDePago")), Integer.parseInt(req.queryParams("presupuestosRequeridos")), Boolean.parseBoolean(req.queryParams("escogerMenor")));
        withTransaction(() -> {
            entidad.agregarEgreso(egreso);
            persist(egreso);
            merge(entidad);
        });
        res.redirect("/entidades/" + entidad.getId() + "/egresos");
        return null;
    }

    public ModelAndView guardar(Request req, Response res, Usuario usuario, Entidad entidad) {
        Optional<Egreso> egresoOptional = obtenerEgreso(req, entidad);
        if(!egresoOptional.isPresent()) {
            res.redirect("/entidades/" + entidad.getId() + "/egresos");
            return null;
        }
        Egreso egreso = egresoOptional.get();

        withTransaction(() -> {
            egreso.setFecha(LocalDate.parse(req.queryParams("fecha")));
            egreso.setMedioDePago(MedioDePago.valueOf(req.queryParams("medioDePago")));
            egreso.setPresupuestosRequeridos(Integer.parseInt(req.queryParams("presupuestosRequeridos")));
            egreso.setEscogerMenor(Boolean.parseBoolean(req.queryParams("escogerMenor")));
        });
        res.redirect("/entidades/" + entidad.getId() + "/egresos");
        return null;
    }
}
