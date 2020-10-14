package Localizacion;

import Main.IDGenerator;
import org.codehaus.jackson.annotate.JsonProperty;
import javax.persistence.Entity;
import java.util.List;

@Entity
public class Pais extends IDGenerator  {
    @JsonProperty("id") private String idAPI;
    @JsonProperty("name") private String nombre;

    public void setIdAPI(String idAPI) { this.idAPI = idAPI; }
    public String getIdAPI() {
        return idAPI;
    }
    public String getNombre() {
        return nombre;
    }

    public Pais() {}

    public Pais(String idAPI, String nombre) {
        this.idAPI = idAPI;
        this.nombre = nombre;
    }

    public List<Provincia> provincias() {
        return Localizacion.servicio().obtenerProvincias(this);
    }

    public Moneda moneda() {
        return Localizacion.servicio().obtenerMoneda(this);
    }
}
