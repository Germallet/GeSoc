package Organizaciones;
import javax.persistence.*;
import java.util.List;


@Entity
public class Organizacion {

    @Id
    @GeneratedValue
    private long id_org;
    @OneToMany
    List<Entidad> entidades;
    @ManyToMany
    List<Etiqueta> etiquetas;
    @ManyToMany
    List<Categoria> categorias;

    public void validarEgresos() {
        entidades.forEach(entidad -> entidad.validarEgresos());
    }
}
