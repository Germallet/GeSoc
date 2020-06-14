package Localizacion;

public class Localizacion {
    private static ServicioLocalizacion servicio = null;

    private Localizacion() {
        this.servicio = new ServicioMercadoLibre();
    }

    void setServicio(ServicioLocalizacion servicio) {
        this.servicio = servicio;
    }

    public static ServicioLocalizacion servicio() {
        if (servicio == null)
            servicio = new ServicioMercadoLibre();
        return servicio;
    }
}
