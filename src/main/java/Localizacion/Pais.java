package MercadoLibre;

import java.util.List;

public class Pais {
    private String nombre;

    public Pais(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Provincia> provincias() {
        return MercadoLibreAPI.servicio().obtenerProvincias(this);
    }
}
