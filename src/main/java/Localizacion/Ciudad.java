package Localizacion;

import Main.IDGenerator;
import org.codehaus.jackson.annotate.JsonProperty;
import javax.persistence.*;

@Entity
public class Ciudad extends IDGenerator {
    @JsonProperty("id") private String idAPI;
    @JsonProperty("name") private String nombre;
    @ManyToOne
    Provincia provincia;

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
