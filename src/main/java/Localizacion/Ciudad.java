package Localizacion;

import org.codehaus.jackson.annotate.JsonProperty;
import javax.persistence.*;

@Embeddable
public class Ciudad {
    @JsonProperty("id") @Column(name="idCiudadAPI") private String idAPI;
    @Transient
    @JsonProperty("name") private String nombre;
    @Transient
    private Provincia provincia;

    public Ciudad() {}

    public Ciudad(String idAPI, String nombre) {
        this.idAPI = idAPI;
        this.nombre = nombre;
    }

    public void setIdAPI(String idAPI) { this.idAPI = idAPI; }
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
