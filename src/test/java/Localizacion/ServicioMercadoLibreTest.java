package Localizacion;

import org.junit.*;
import java.util.List;
import org.mockito.Mockito;

public class ServicioMercadoLibreTest {

    private ServicioMercadoLibre servicio;

    @Before
    public void inicializarTest() {
        servicio = Mockito.spy(new ServicioMercadoLibre());

        Mockito.doReturn(
                "[{\"id\":\"BO\",\"name\":\"Bolivia\",\"locale\":\"es_BO\",\"currency_id\":\"BOB\"}," +
                "{\"id\":\"BR\",\"name\":\"Brasil\",\"locale\":\"pt_BR\",\"currency_id\":\"BRL\"}," +
                "{\"id\":\"CL\",\"name\":\"Chile\",\"locale\":\"es_CL\",\"currency_id\":\"CLP\"}]"
        ).when(servicio).obtenerStringJSON("classified_locations/countries");

        Mockito.doReturn(
                "{\"id\":\"AR\",\"name\":\"Argentina\",\"locale\":\"es_AR\",\"currency_id\":\"ARS\"," +
                "\"states\":[{\"id\":\"TUxBUENPU2ExMmFkMw\",\"name\":\"Bs.As. Costa Atlántica\"}," +
                            "{\"id\":\"idBuenosAiresInterior\",\"name\":\"Buenos Aires Interior\"}," +
                            "{\"id\":\"TUxBUENBUGw3M2E1\",\"name\":\"Capital Federal\"}]}"
        ).when(servicio).obtenerStringJSON("classified_locations/countries/AR");

        Mockito.doReturn(
                "{\"id\":\"idBuenosAiresInterior\",\"name\":\"Buenos Aires Interior\"," +
                        "\"country\":{\"id\":\"AR\",\"name\":\"Argentina\"}," +
                        "\"cities\":[{\"id\":\"TUxBQzI1RDIwMzQ\",\"name\":\"25 de Mayo\"}," +
                        "{\"id\":\"TUxBQ0FMQjYwODM\",\"name\":\"Alberti\"}," +
                        "{\"id\":\"TUxBQ0FSUjM5OTg\",\"name\":\"Arrecifes\"}]}"
        ).when(servicio).obtenerStringJSON("classified_locations/states/idBuenosAiresInterior");

        Mockito.doReturn(
                "{\"id\": \"idChivilcoy\", \"name\": \"Chivilcoy\",\n" +
                        "\"state\": {\"id\": \"TUxBUFpPTmFpbnRl\", \"name\": \"Buenos Aires Interior\"},\n" +
                        "\"country\": {\"id\": \"AR\", \"name\": \"Argentina\"},\n" +
                        "\"neighborhoods\": [\n" +
                        "{\"id\": \"TUxBQkNISTI2MTBa\", \"name\": \"Chivilcoy\"},\n" +
                        "{\"id\": \"TUxBQkVNSTg2MzNa\", \"name\": \"Emilio Ayarza\"}\n" +
                        "]}"
        ).when(servicio).obtenerStringJSON("classified_locations/cities/idChivilcoy");

        Mockito.doReturn(
                "[{\"id\":\"ARS\",\"symbol\":\"$\",\"description\":\"Peso argentino\",\"decimal_places\":2}," +
                        "{\"id\":\"BOB\",\"symbol\":\"Bs\",\"description\":\"Boliviano\",\"decimal_places\":2}," +
                        "{\"id\":\"BRL\",\"symbol\":\"R$\",\"description\":\"Real\",\"decimal_places\":2}]"
        ).when(servicio).obtenerStringJSON("currencies");

        Mockito.doReturn(
                "{\"id\":\"ARS\",\"symbol\":\"$\",\"description\":\"Peso argentino\",\"decimal_places\":2}"
        ).when(servicio).obtenerStringJSON("currencies/ARS");
    }

    @Test
    public void paises() {
        List<Pais> paises = servicio.obtenerPaises();
        Assert.assertTrue(!paises.isEmpty());
    }

    @Test
    public void provincias() {
        Pais argentina = new Pais();
        argentina.setIdAPI("AR");

        List<Provincia> provincias = servicio.obtenerProvincias(argentina);
        Assert.assertTrue(!provincias.isEmpty());
    }

    @Test
    public void ciudades() {
        Provincia BsAsInterior = new Provincia();
        BsAsInterior.setIdAPI("idBuenosAiresInterior");

        List<Ciudad> ciudades = servicio.obtenerCiudades(BsAsInterior);
        Assert.assertTrue(!ciudades.isEmpty());
    }

    @Test
    public void ciudad() {
        Ciudad chivilcoy = servicio.obtenerCiudad("idChivilcoy");
        Assert.assertEquals("Chivilcoy", chivilcoy.getNombre());
        Assert.assertEquals("Buenos Aires Interior", chivilcoy.getProvincia().getNombre());
        Assert.assertEquals("Argentina", chivilcoy.getProvincia().getPais().getNombre());
    }

    @Test
    public void monedas() {
        List<Moneda> monedas = servicio.obtenerMonedas();
        Assert.assertTrue(!monedas.isEmpty());
    }

    @Test
    public void moneda() {
        Pais argentina = new Pais();
        argentina.setIdAPI("AR");

        Moneda pesosArg = servicio.obtenerMoneda(argentina);
        Assert.assertEquals("Peso argentino", pesosArg.getDescripcion());
    }
}
