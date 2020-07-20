package Localizacion;

public class DireccionPostal {
    private String calle;
    private String piso;
    private int altura;
    private Ciudad ciudad;

    public DireccionPostal(String calle, String piso, int altura, Ciudad ciudad) {
        this.calle = calle;
        this.piso = piso;
        this.altura = altura;
        this.ciudad = ciudad;
    }
}
