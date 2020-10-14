package Organizaciones;

import Main.IDGenerator;
import javax.persistence.*;
import java.util.List;

@Entity
public class Organizacion extends IDGenerator {

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="organizacion_id")
    List<Entidad> entidades;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="organizacion_id")
    List<Etiqueta> etiquetas;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="organizacion_id")
    List<Categoria> categorias;

    public Organizacion() {}

    public Organizacion(List<Entidad> entidades, List<Etiqueta> etiquetas, List<Categoria> categorias){
        this.entidades = entidades;
        this.categorias = categorias;
        this.etiquetas = etiquetas;
    }

    public void validarEgresos() {
        entidades.forEach(Entidad::validarEgresos);
    }
}
