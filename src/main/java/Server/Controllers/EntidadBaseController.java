package Server.Controllers;

import Organizaciones.*;
import Persistence.WithGlobalEntityManagerEnv;
import Seguridad.Usuario;
import org.uqbarproject.jpa.java8.extras.*;
import org.uqbarproject.jpa.java8.extras.transaction.*;
import spark.*;
import java.util.Optional;

public class EntidadBaseController implements WithGlobalEntityManagerEnv, EntityManagerOps, TransactionalOps {
    public ModelAndView guardar(Request req, Response res, Usuario usuario) {
        Optional<Entidad> entidadOptional = usuario.getEntidadConId(req.params("idEntidad"));
        if(!entidadOptional.isPresent())
            return null;
        Optional<Categoria> categoriaOptional = usuario.getOrganizacion().getCategorias().stream().filter(cat -> Long.parseLong(req.queryParams("categoria")) == cat.getId()).findAny();

        Base entidad = (Base)entidadOptional.get();
        withTransaction(() -> {
            entidad.setNombreFicticio(req.queryParams("nombreFicticio"));
            entidad.setDescripcion(req.queryParams("descripcion"));
            entidad.setCategoria(categoriaOptional.orElse(null));
        });

        res.redirect("/entidades");
        return null;
    }
}
