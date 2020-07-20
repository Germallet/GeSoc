package Localizacion;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class Pais {
    @JsonProperty("id") private String idAPI;
    @JsonProperty("name") private String nombre;

    public void setIdAPI(String idAPI) { this.idAPI = idAPI; }
    public String getIdAPI() {
        return idAPI;
    }
    public String getNombre() {
        return nombre;
    }

    public List<Provincia> provincias() {
        return Localizacion.servicio().obtenerProvincias(this);
    }

    public Moneda moneda() {
        return Localizacion.servicio().obtenerMoneda(this);
    }
}
