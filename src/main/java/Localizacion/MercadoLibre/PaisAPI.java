package Localizacion;

import java.util.List;

class PaisAPI {
    public String id;
    public String name;
    public String locale;
    public String currency_id;
    public String decimal_separator;
    public String thousands_separator;
    public String time_zone;
    public GeoInformation geo_information;
    public List<ProvinciaAPI> states;

    private class GeoInformation {
        public Location location;

        private class Location {
            public double latitude;
            public double longitude;
        }
    }

    public Pais generarPais() {
        return new Pais(name);
    }
}
