package Localizacion;

import java.io.IOException;
import java.util.List;
import com.sun.jersey.api.client.*;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.type.TypeReference;

import javax.ws.rs.core.MediaType;

public class ServicioMercadoLibre implements ServicioLocalizacion {
    private Client client;

    public ServicioMercadoLibre() {
        this.client = Client.create();
    }

    private ClientResponse comunicarseConAPI(String path){
        ClientResponse response = this.client
                .resource("https://api.mercadolibre.com/")
                .path(path)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        return response;
    }
    private String obtenerStringJSON(String path){
        return comunicarseConAPI(path).getEntity(String.class);
    }

    private ObjectMapper mapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    @Override
    public List<Pais> obtenerPaises() {
        String respuesta = obtenerStringJSON("classified_locations/countries");
        try {
            return mapper().readValue(respuesta, new TypeReference<List<Pais>>(){});
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Provincia> obtenerProvincias(Pais pais) {
        String respuesta = obtenerStringJSON("classified_locations/countries/" + pais.getIdAPI());
        try {
            ObjectMapper mapper = mapper();
            String states = mapper.readTree(respuesta).findPath("states").toString();
            List<Provincia> provincias = mapper.readValue(states, new TypeReference<List<Provincia>>(){});
            provincias.forEach(provincia -> provincia.setPais(pais));
            return provincias;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Ciudad> obtenerCiudades(Provincia provincia) {
        String respuesta = obtenerStringJSON("classified_locations/states/" + provincia.getIdAPI());
        try {
            ObjectMapper mapper = mapper();
            String cities = mapper.readTree(respuesta).findPath("cities").toString();
            List<Ciudad> ciudades = mapper.readValue(cities, new TypeReference<List<Ciudad>>(){});
            ciudades.forEach(ciudad -> ciudad.setProvincia(provincia));
            return ciudades;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Moneda> obtenerMonedas() {
        String respuesta = obtenerStringJSON("currencies");

        try {
            return mapper().readValue(respuesta, new TypeReference<List<Moneda>>(){});
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private String obtenerIdMoneda(Pais pais) {
        String respuesta = obtenerStringJSON("classified_locations/countries/" + pais.getIdAPI());

        try {
            return mapper().readTree(respuesta).findPath("currency_id").asText();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    @Override
    public Moneda obtenerMoneda(Pais pais) {
        String respuesta = obtenerStringJSON("currencies/" + obtenerIdMoneda(pais));

        try {
            return mapper().readValue(respuesta, Moneda.class);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
