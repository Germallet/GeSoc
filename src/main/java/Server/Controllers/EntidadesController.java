package Server.Controllers;

import Organizaciones.*;
import Seguridad.*;
import Server.ControllerConUsuario;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.*;
import java.util.*;
import java.util.stream.Collectors;

public class EntidadesController implements ControllerConUsuario, WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
    public ModelAndView listar(Request req, Response res) {
        if (!estaLogueado(req)) {
            res.redirect("/login");
            return null;
        }
        Usuario usuario = obtenerUsuario(req);

        List<Entidad> entidades;
        String búsquedaCategoría = req.queryParams("categoria");
        if (búsquedaCategoría == null || búsquedaCategoría.equals(""))
            entidades = usuario.getOrganizacion().getEntidades();
        else
            entidades = usuario.getOrganizacion().getEntidades().stream().filter(entidad -> entidad.getCategoria() != null && entidad.getCategoria().getNombre().contains(búsquedaCategoría)).collect(Collectors.toList());

        Map<String, Object> model = new HashMap<>();
        model.put("usuario", usuario);
        model.put("entidades", entidades);
        model.put("busqueda", búsquedaCategoría);
        return new ModelAndView(model, "entidades/listar.hbs");
    }

    public ModelAndView mostrar(Request req, Response res) {
        if (!estaLogueado(req)) {
            res.redirect("/login");
            return null;
        }
        Usuario usuario = obtenerUsuario(req);

        Optional<Entidad> entidad = usuario.getOrganizacion().getEntidades().stream().filter(e -> Long.toString(e.getId()).equals(req.params("id"))).findAny();
        if(!entidad.isPresent()) {
            res.redirect("/entidades");
            return null;
        }

        Map<String, Object> model = new HashMap<>();
        model.put("usuario", usuario);
        model.put("entidad", entidad.get());

        return new ModelAndView(model, "entidades/mostrar" + entidad.get().getClass().getSimpleName() + ".hbs");
    }
}
