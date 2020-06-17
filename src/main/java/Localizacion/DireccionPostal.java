package Localizacion;

public class DireccionPostal {
    private String calle;
    private String piso;
    private int altura;
    private Ciudad ciudad;
    private Pais pais;
    private Provincia provincia;

    public DireccionPostal(String calle, String piso, int altura, Ciudad ciudad, Pais pais, Provincia provincia) {
        this.calle = calle;
        this.piso = piso;
        this.altura = altura;
        this.ciudad = ciudad;
        this.pais = pais;
        this.provincia = provincia;
    }
}
