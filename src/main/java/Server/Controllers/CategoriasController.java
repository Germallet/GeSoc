package Server.Controllers;

import Organizaciones.Categoria;
import Organizaciones.ComportamientoPermitirEgreso.*;
import Seguridad.*;
import org.uqbarproject.jpa.java8.extras.*;
import org.uqbarproject.jpa.java8.extras.transaction.*;
import spark.*;
import java.util.*;

public class CategoriasController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
    public ModelAndView listar(Request req, Response res, Usuario usuario) {
        Map<String, Object> model = new HashMap<>();
        model.put("usuario", usuario);
        model.put("categorias", usuario.getOrganizacion().getCategorias());
        return new ModelAndView(model, "categorias/categorias.hbs");
    }

    public ModelAndView crear(Request req, Response res, Usuario usuario) {
        withTransaction(() -> {
            ComportamientoPermitirEgreso comportamientoPermitirEgreso = new ComportamientoPermitirEgreso_Permitir();
            persist(comportamientoPermitirEgreso);
            Categoria nuevaCategoria = new Categoria("Nueva Categoría", true, true, comportamientoPermitirEgreso);
            usuario.getOrganizacion().getCategorias().add(nuevaCategoria);
            persist(nuevaCategoria);
        });

        res.redirect("/categorias");
        return null;
    }

    public ModelAndView borrar(Request req, Response res, Usuario usuario) {
        Optional<Categoria> categoria = usuario.getCategoriaConId(req.params("id"));
        if (categoria.isPresent())
        {
            withTransaction(() -> {
                usuario.getOrganizacion().getEntidades().stream().filter(entidad -> entidad.perteneceACategoria(categoria.get())).forEach(entidad -> {
                    entidad.setCategoria(null);
                    merge(entidad);
                });
                usuario.getOrganizacion().getCategorias().remove(categoria.get());
                remove(categoria.get());
            });
        }

        res.redirect("/categorias");
        return null;
    }

    public ModelAndView editar(Request req, Response res, Usuario usuario) {
        Optional<Categoria> categoria = usuario.getCategoriaConId(req.params("id"));
        if (categoria.isPresent())
        {
            withTransaction(() -> {
                categoria.get().setNombre(req.queryParams("nombre"));
                categoria.get().setPermiteEntidadBase(Boolean.parseBoolean(req.queryParams("permiteEntidadBase")));
                categoria.get().setPuedeSerDeJuridica(Boolean.parseBoolean(req.queryParams("puedeSerDeJuridica")));
            });
        }

        res.redirect("/categorias");
        return null;
    }
}
