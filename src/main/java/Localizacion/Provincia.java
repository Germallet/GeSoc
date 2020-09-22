package Localizacion;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class Provincia {
    @JsonProperty("id") private String idAPI;
    @JsonProperty("name") private String nombre;
    private Pais pais;

    public void setIdAPI(String idAPI) { this.idAPI = idAPI; }
    public String getIdAPI() {
        return idAPI;
    }
    public String getNombre() {
        return nombre;
    }

    public Provincia() {}

    public Provincia(String idApi, String nombre) {
        this.idAPI = idApi;
        this.nombre = nombre;
    }

    public Pais getPais() {
        return pais;
    }
    public void setPais(Pais pais) {
        this.pais = pais;
    }
    public List<Ciudad> ciudades() {
        return Localizacion.servicio().obtenerCiudades(this);
    }
}
