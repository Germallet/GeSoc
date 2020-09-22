package Localizacion;

import java.util.List;

public interface ServicioLocalizacion {
    List<Pais> obtenerPaises();
    List<Provincia> obtenerProvincias(Pais pais);
    List<Ciudad> obtenerCiudades(Provincia provincia);
    Ciudad obtenerCiudad(String id);
    List<Moneda> obtenerMonedas();
    Moneda obtenerMoneda(Pais pais);
}
