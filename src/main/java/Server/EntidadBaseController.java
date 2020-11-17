package Server;

import Organizaciones.*;
import Seguridad.Usuario;
import org.uqbarproject.jpa.java8.extras.*;
import org.uqbarproject.jpa.java8.extras.transaction.*;
import spark.*;
import java.util.Optional;

public class EntidadBaseController implements ControllerConUsuario, WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
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

        Base entidad = (Base)entidadOptional.get();
        withTransaction(() -> {
            entidad.setNombreFicticio(req.queryParams("nombreFicticio"));
            entidad.setDescripcion(req.queryParams("descripcion"));
            entidad.setCategoria(categoriaOptional.orElseGet(null));
        });

        return null;
    }
}
