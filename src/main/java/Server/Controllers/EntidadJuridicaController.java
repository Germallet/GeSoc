package Server.Controllers;

import Organizaciones.*;
import Persistence.WithGlobalEntityManagerEnv;
import Seguridad.Usuario;
import org.uqbarproject.jpa.java8.extras.*;
import org.uqbarproject.jpa.java8.extras.transaction.*;
import spark.*;
import java.util.Optional;

public class EntidadJuridicaController implements WithGlobalEntityManagerEnv, EntityManagerOps, TransactionalOps  {
    public ModelAndView guardar(Request req, Response res, Usuario usuario) {
        Optional<Entidad> entidadOptional = usuario.getEntidadConId(req.params("idEntidad"));
        if(!entidadOptional.isPresent())
            return null;
        Optional<Categoria> categoriaOptional = usuario.getOrganizacion().getCategorias().stream().filter(cat -> Long.parseLong(req.queryParams("categoria")) == cat.getId()).findAny();

        Juridica entidad = (Juridica)entidadOptional.get();
        withTransaction(() -> {
            entidad.setNombreFicticio(req.queryParams("nombreFicticio"));
            entidad.setRazonSocial(req.queryParams("razonSocial"));
            entidad.setCUIT(Integer.parseInt(req.queryParams("CUIT")));
            entidad.setDireccionPostal(Integer.parseInt(req.queryParams("direccionPostal")));
            entidad.setCategoria(categoriaOptional.orElse(null));
        });

        res.redirect("/entidades");
        return null;
    }
}
