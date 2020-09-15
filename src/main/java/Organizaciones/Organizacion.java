package Organizaciones;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "organizaciones")
public class Organizacion {

    @Id
    @GeneratedValue
    private long id_org;

    List<Entidad> entidades;
    List<Etiqueta> etiquetas;
    List<Categoria> categorias;

    public void validarEgresos() {
        entidades.forEach(entidad -> entidad.validarEgresos());
    }
}
