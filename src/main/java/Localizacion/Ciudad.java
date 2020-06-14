package Localizacion;

import org.codehaus.jackson.annotate.JsonProperty;

public class Ciudad {
    @JsonProperty("name") private String nombre;

    public String getNombre() {
        return nombre;
    }
}
