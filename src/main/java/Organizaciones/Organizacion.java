package Organizaciones;
import Egresos.Egreso;
import java.util.List;

public class Organizacion {
    List<Entidad> entidades;
    List<Etiqueta> etiquetas;
    List<Categoria> categorias;

    public void validarEgresos() {
        entidades.forEach(entidad -> entidad.validarEgresos());
    }



}
