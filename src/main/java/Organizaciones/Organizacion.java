package Organizaciones;

import Main.IDGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Organizacion extends IDGenerator {

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="organizacion_id")
    List<Entidad> entidades = new ArrayList<>();
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="organizacion_id")
    List<Etiqueta> etiquetas = new ArrayList<>();
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="organizacion_id")
    List<Categoria> categorias = new ArrayList<>();

    public Organizacion() {}

    public Organizacion(List<Entidad> entidades, List<Etiqueta> etiquetas, List<Categoria> categorias){
        this.entidades = entidades;
        this.categorias = categorias;
        this.etiquetas = etiquetas;
    }

    public List<Entidad> getEntidades() { return entidades; }
    public List<Etiqueta> getEtiquetas() { return etiquetas; }
    public List<Categoria> getCategorias() { return categorias; }

    public void validarEgresos() {
        entidades.forEach(Entidad::validarEgresos);
    }
}
