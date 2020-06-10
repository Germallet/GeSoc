package MercadoLibre;

import java.util.List;

public class Provincia {
    private Pais pais;
    private String nombre;

    public Provincia(Pais pais, String nombre) {
        this.pais = pais;
        this.nombre = nombre;
    }

    public Pais getPais() {
        return pais;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Ciudad> ciudades() {
        return MercadoLibreAPI.servicio().obtenerCiudades(this);
    }
}
