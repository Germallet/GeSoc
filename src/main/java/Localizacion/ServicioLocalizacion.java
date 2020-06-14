package Localizacion;

import java.util.List;

public interface ServicioLocalizacion {
    List<Pais> obtenerPaises();
    List<Provincia> obtenerProvincias(Pais pais);
    List<Ciudad> obtenerCiudades(Provincia provincia);
    List<Moneda> obtenerMonedas();
    Moneda obtenerMoneda(Pais pais);
}
