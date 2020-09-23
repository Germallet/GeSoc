package Localizacion;

import org.codehaus.jackson.annotate.JsonProperty;

public class Ciudad {
    @JsonProperty("id") private String idAPI;
    @JsonProperty("name") private String nombre;
    private Provincia provincia;

    public Ciudad() {}

    public Ciudad(String idAPI, String nombre) {
        this.idAPI = idAPI;
        this.nombre = nombre;
    }

    public String getIdAPI() {
        return idAPI;
    }
    public String getNombre() {
        return nombre;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
    public Provincia getProvincia() {
        return provincia;
    }
}
