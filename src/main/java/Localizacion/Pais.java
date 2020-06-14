package Localizacion;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class Pais {
    private String id;
    @JsonProperty("name") private String nombre;

    public String getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public List<Provincia> provincias() {
        return Localizacion.servicio().obtenerProvincias(this);
    }
}
