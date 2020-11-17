package Server.Controllers;

import Organizaciones.*;
import Seguridad.Usuario;
import org.uqbarproject.jpa.java8.extras.*;
import org.uqbarproject.jpa.java8.extras.transaction.*;
import spark.*;
import java.util.*;
import java.util.stream.Collectors;

public class EntidadesController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
    public ModelAndView listar(Request req, Response res, Usuario usuario) {
        Optional<Categoria> categoria = usuario.getCategoriaConId(req.queryParams("categoria"));
        List<Entidad> entidades = categoria.isPresent() ?
                usuario.getOrganizacion().getEntidades().stream().filter(entidad -> entidad.perteneceACategoria(categoria.get())).collect(Collectors.toList())
                :
                usuario.getOrganizacion().getEntidades();

        Map<String, Object> model = new HashMap<>();
        model.put("usuario", usuario);
        model.put("entidades", entidades);
        model.put("busqueda", categoria.isPresent() ? categoria.get().getId() : "");
        return new ModelAndView(model, "entidades/listar.hbs");
    }

    public ModelAndView mostrar(Request req, Response res, Usuario usuario) {
        Optional<Entidad> entidad = usuario.getEntidadConId(req.params("idEntidad"));
        if (!entidad.isPresent()) {
            res.redirect("/entidades");
            return null;
        }

        Map<String, Object> model = new HashMap<>();
        model.put("usuario", usuario);
        model.put("entidad", entidad.get());

        return new ModelAndView(model, "entidades/mostrar" + entidad.get().getClass().getSimpleName() + ".hbs");
    }
}
