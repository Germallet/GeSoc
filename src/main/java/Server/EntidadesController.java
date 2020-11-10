package Server;

import Organizaciones.*;
import Seguridad.*;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.*;
import java.util.*;

public class EntidadesController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
    public ModelAndView listar(Request req, Response res) {
        Usuario usuario = req.session().attribute("usuario");
        if (usuario == null) {
            res.redirect("/login");
            return null;
        }

        Map<String, Object> model = new HashMap<>();
        model.put("usuario", usuario);
        model.put("entidades", usuario.getOrganizacion().getEntidades());
        return new ModelAndView(model, "entidades/listar.hbs");
    }

    public ModelAndView mostrar(Request req, Response res) {
        Usuario usuario = req.session().attribute("usuario");
        if (usuario == null) {
            res.redirect("/login");
            return null;
        }

        Optional<Entidad> entidad = usuario.getOrganizacion().getEntidades().stream().filter(e -> Long.toString(e.getId()).equals(req.params("id"))).findAny();
        if(!entidad.isPresent()) {
            res.redirect("/entidades");
            return null;
        }

        Map<String, Object> model = new HashMap<>();
        model.put("usuario", usuario);
        model.put("entidad", entidad.get());

        if (entidad.get() instanceof Base)
            return new ModelAndView(model, "entidades/mostrarBase.hbs");
        else
            return new ModelAndView(model, "entidades/mostrarJuridica.hbs");
    }

    public ModelAndView guardar(Request req, Response res) {
        Usuario usuario = req.session().attribute("usuario");
        if (usuario == null) {
            res.redirect("/login");
            return null;
        }
        res.redirect("/entidades");

        Optional<Entidad> entidadOptional = usuario.getOrganizacion().getEntidades().stream().filter(e -> Long.toString(e.getId()).equals(req.params("id"))).findAny();
        if(!entidadOptional.isPresent())
            return null;
        Optional<Categoria> categoriaOptional = usuario.getOrganizacion().getCategorias().stream().filter(cat -> Long.toString(cat.getId()).equals(req.queryParams("categoria"))).findAny();

        if(entidadOptional.get() instanceof Base) {
            Base entidad = (Base)entidadOptional.get();
            withTransaction(() -> {
                entidad.setNombreFicticio(req.queryParams("nombreFicticio"));
                entidad.setDescripcion(req.queryParams("descripcion"));
                entidad.setCategoria(categoriaOptional.orElseGet(null));
            });
        } else {
            Juridica entidad = (Juridica)entidadOptional.get();
            withTransaction(() -> {
                entidad.setNombreFicticio(req.queryParams("nombreFicticio"));
                entidad.setRazonSocial(req.queryParams("razonSocial"));
                entidad.setCUIT(Integer.parseInt(req.queryParams("CUIT")));
                entidad.setDireccionPostal(Integer.parseInt(req.queryParams("direccionPostal")));
                entidad.setCategoria(categoriaOptional.orElseGet(null));
            });
        }

        return null;
    }
}
