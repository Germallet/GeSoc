package Localizacion;

import javax.persistence.*;

@Embeddable
public class DireccionPostal {
    @Column(name="direccion_calle") private String calle;
    @Column(name="direccion_piso") private String piso;
    @Column(name="direccion_altura") private int altura;
    @ManyToOne
    @JoinColumn(name="direccion_ciudad") private Ciudad ciudad;

    public DireccionPostal(String calle, String piso, int altura, Ciudad ciudad) {
        this.calle = calle;
        this.piso = piso;
        this.altura = altura;
        this.ciudad = ciudad;
    }

    public DireccionPostal() {
        super();
    }

    public Ciudad getCiudad() {
        return ciudad;
    }
}
