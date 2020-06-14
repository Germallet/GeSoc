package Localizacion;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class Provincia {
    private String id;
    @JsonProperty("name") private String nombre;

    public String getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public List<Ciudad> ciudades() {
        return Localizacion.servicio().obtenerCiudades(this);
    }
}
