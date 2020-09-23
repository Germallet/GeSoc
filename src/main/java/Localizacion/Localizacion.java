package Localizacion;

public class Localizacion {
    private static ServicioLocalizacion servicio = null;

    private Localizacion() {
        this.servicio = new ServicioMercadoLibre();
    }

    public static void setServicio(ServicioLocalizacion nuevoServicio) {
        servicio = nuevoServicio;
    }

    public static ServicioLocalizacion servicio() {
        if (servicio == null)
            servicio = new ServicioMercadoLibre();
        return servicio;
    }
}
