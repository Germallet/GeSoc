package Localizacion;

public class CiudadAPI {
    public String id;
    public String name;

    public Ciudad generarCiudad() {
        return new Ciudad(name);
    }
}
