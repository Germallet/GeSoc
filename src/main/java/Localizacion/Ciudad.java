package Localizacion;

import org.codehaus.jackson.annotate.JsonProperty;

public class Ciudad {
    @JsonProperty("name") private String nombre;
    private Provincia provincia;

    public String getNombre() {
        return nombre;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
}
