package Server;

import Organizaciones.*;
import Seguridad.*;
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

        if (entidad.get() instanceof Base)
            return new ModelAndView(model, "entidades/mostrarBase.hbs");
        else
            return new ModelAndView(model, "entidades/mostrarJuridica.hbs");
    }

    public ModelAndView guardar(Request req, Response res) {
        if (!estaLogueado(req)) {
            res.redirect("/login");
            return null;
        }
        Usuario usuario = obtenerUsuario(req);
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
