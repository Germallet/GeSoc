package Organizaciones;
import Main.IDGenerator;

import javax.persistence.*;
import java.util.List;


@Entity
public class Organizacion extends IDGenerator {

    private long id_org;
    @OneToMany
    List<Entidad> entidades;
    @ManyToMany
    List<Etiqueta> etiquetas;
    @ManyToMany
    List<Categoria> categorias;

    public Organizacion(long id, List<Entidad> entidades, List<Etiqueta> etiquetas, List<Categoria> categorias){
        this.entidades = entidades;
        this.categorias = categorias;
        this.id_org = id;
        this.etiquetas = etiquetas;
    }

    public long getId(){
        return id_org;
    }

    public void validarEgresos() {
        entidades.forEach(entidad -> entidad.validarEgresos());
    }
}
