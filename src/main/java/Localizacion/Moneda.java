package Localizacion;

import org.codehaus.jackson.annotate.JsonProperty;

public class Moneda {
    @JsonProperty("description") private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }
}
