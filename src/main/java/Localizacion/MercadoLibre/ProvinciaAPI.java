package Localizacion;

import java.util.List;

public class ProvinciaAPI {
    public String id;
    public String name;
    public PaisAPI country;
    public GeoInformation geo_information;
    public String time_zone;
    public List<CiudadAPI> cities;

    private class GeoInformation {
        public Location location;

        private class Location {
            public double latitude;
            public double longitude;
        }
    }

    public Provincia generarProvincia(Pais pais) {
        return new Provincia(pais, name);
    }
}
