package Server;

import Organizaciones.Entidad;
import Seguridad.Usuario;
import org.uqbarproject.jpa.java8.extras.*;
import org.uqbarproject.jpa.java8.extras.transaction.*;
import spark.*;
import java.time.LocalDate;
import java.util.*;
import Egresos.*;

public class EgresosController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

    public ModelAndView listar(Request req, Response res) {
        Usuario usuario = req.session().attribute("usuario");
        if (usuario == null) {
            res.redirect("/login");
            return null;
        }

        Optional<Entidad> entidad = usuario.getOrganizacion().getEntidades().stream().filter(e -> Long.toString(e.getId()).equals(req.params("idEntidad"))).findAny();
        if(!entidad.isPresent()) {
            res.redirect("/entidades");
            return null;
        }

        Map<String, Object> model = new HashMap<>();
        model.put("entidad", entidad.get());
        model.put("egresos", entidad.get().getEgresos());
        model.put("usuario", usuario);
        return new ModelAndView(model, "egresos/listar.hbs");
    }

    public ModelAndView mostrar(Request req, Response res) {
        Usuario usuario = req.session().attribute("usuario");
        if (usuario == null) {
            res.redirect("/login");
            return null;
        }

        Optional<Entidad> entidad = usuario.getOrganizacion().getEntidades().stream().filter(e -> Long.toString(e.getId()).equals(req.params("idEntidad"))).findAny();
        if(!entidad.isPresent()) {
            res.redirect("/entidades");
            return null;
        }

        Optional<Egreso> egreso = entidad.get().getEgresos().stream().filter(e -> Long.toString(e.getId()).equals(req.params("idEgreso"))).findAny();
        if(!egreso.isPresent()) {
            res.redirect("/entidades/" + entidad.get().getId() + "/egresos");
            return null;
        }

        String medioDePago;
        switch(egreso.get().getMedioDePago()) {
            case TarjetaCredito: medioDePago = "tarjetaCredito"; break;
            case TarjetaDebito: medioDePago = "tarjetaDebito"; break;
            case Efectivo: medioDePago = "efectivo"; break;
            case CajeroAutomatico: medioDePago = "cajeroAutomatico"; break;
            case DineroEnCuenta: medioDePago = "dineroEnCuenta"; break;
            default: throw new IllegalArgumentException();
        }

        Map<String, Object> model = new HashMap<>();
        model.put("usuario", usuario);
        model.put("entidad", entidad.get());
        model.put("egreso", egreso.get());
        model.put(medioDePago, true);
        return new ModelAndView(model, "egresos/mostrar.hbs");
    }

    public ModelAndView nuevo(Request req, Response res) {
        Usuario usuario = req.session().attribute("usuario");
        if (usuario == null) {
            res.redirect("/login");
            return null;
        }

        Optional<Entidad> entidad = usuario.getOrganizacion().getEntidades().stream().filter(e -> Long.toString(e.getId()).equals(req.params("idEntidad"))).findAny();
        if(!entidad.isPresent()) {
            res.redirect("/entidades");
            return null;
        }

        Map<String, Object> model = new HashMap<>();
        model.put("usuario", usuario);
        model.put("entidad", entidad.get());
        return new ModelAndView(model, "egresos/new.hbs");
    }

    public ModelAndView crear(Request req, Response res) {
        Usuario usuario = req.session().attribute("usuario");
        if (usuario == null) {
            res.redirect("/login");
            return null;
        }

        Optional<Entidad> entidad = usuario.getOrganizacion().getEntidades().stream().filter(e -> Long.toString(e.getId()).equals(req.params("idEntidad"))).findAny();
        if(!entidad.isPresent()) {
            res.redirect("/entidades");
            return null;
        }

        Egreso egreso = new Egreso(null, LocalDate.parse(req.queryParams("fecha")), MedioDePago.valueOf(req.queryParams("medioDePago")), Integer.parseInt(req.queryParams("presupuestosRequeridos")), Boolean.parseBoolean(req.queryParams("escogerMenor")));
        withTransaction(() -> {
            entidad.get().agregarEgreso(egreso);
            persist(egreso);
            merge(entidad.get());
        });
        res.redirect("/entidades/" + entidad.get().getId() + "/egresos");
        return null;
    }

    public ModelAndView guardar(Request req, Response res) {
        Usuario usuario = req.session().attribute("usuario");
        if (usuario == null) {
            res.redirect("/login");
            return null;
        }

        Optional<Entidad> entidad = usuario.getOrganizacion().getEntidades().stream().filter(e -> Long.toString(e.getId()).equals(req.params("idEntidad"))).findAny();
        if(!entidad.isPresent()) {
            res.redirect("/entidades");
            return null;
        }

        Optional<Egreso> egresoOptional = entidad.get().getEgresos().stream().filter(e -> Long.toString(e.getId()).equals(req.params("idEgreso"))).findAny();
        if(!egresoOptional.isPresent()) {
            res.redirect("/entidades/" + entidad.get().getId() + "/egresos");
            return null;
        }
        Egreso egreso = egresoOptional.get();

        withTransaction(() -> {
            egreso.setFecha(LocalDate.parse(req.queryParams("fecha")));
            egreso.setMedioDePago(MedioDePago.valueOf(req.queryParams("medioDePago")));
            egreso.setPresupuestosRequeridos(Integer.parseInt(req.queryParams("presupuestosRequeridos")));
            egreso.setEscogerMenor(Boolean.parseBoolean(req.queryParams("escogerMenor")));
            merge(egreso);
        });
        res.redirect("/entidades/" + entidad.get().getId() + "/egresos");
        return null;
    }
}
