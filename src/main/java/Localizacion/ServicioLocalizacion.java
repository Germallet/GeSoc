package Localizacion;

import java.util.List;

public interface ServicioLocalizacion {
    List<Pais> obtenerPaises();
    List<Provincia> obtenerProvincias(Pais pais);
    List<Ciudad> obtenerCiudades(Provincia provincia);
}
