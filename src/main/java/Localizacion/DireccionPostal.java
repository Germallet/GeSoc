package Localizacion;

import Main.IDGenerator;
import javax.persistence.*;

@Entity
public class DireccionPostal extends IDGenerator {
    private String calle;
    private String piso;
    private int altura;
    @Embedded
    @Convert(converter = CiudadConverter.class, attributeName="idCiudadAPI")
    private Ciudad ciudad;

    public DireccionPostal(String calle, String piso, int altura, Ciudad ciudad) {
        this.calle = calle;
        this.piso = piso;
        this.altura = altura;
        this.ciudad = ciudad;
    }

    public DireccionPostal() {
        super();
    }
}
