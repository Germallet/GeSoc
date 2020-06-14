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
        ObjectMapper mapper = mapper();
        String respuesta = obtenerStringJSON("classified_locations/countries/" + pais.getId());

        try {
            String states = mapper.readTree(respuesta).findPath("states").toString();
            return mapper.readValue(states, new TypeReference<List<Provincia>>(){});
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    @Override
    public List<Ciudad> obtenerCiudades(Provincia provincia) {
        ObjectMapper mapper = mapper();
        String respuesta = obtenerStringJSON("classified_locations/states/" + provincia.getId());

        try {
            String cities = mapper.readTree(respuesta).findPath("cities").toString();
            return mapper.readValue(cities, new TypeReference<List<Ciudad>>(){});
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
