package Localizacion;

import org.junit.*;
import java.util.List;
import org.mockito.Mockito;

public class ServicioMercadoLibreTest {

    private ServicioLocalizacion servicio;

    @Before
    public void inicializarTest() {
        servicio = new ServicioMercadoLibre();
    }

    @Test
    public void paises() {
        List<Pais> paises = servicio.obtenerPaises();
        Assert.assertTrue(!paises.isEmpty());
    }

    @Test
    public void provincias() {
        Pais argentina = Mockito.mock(Pais.class);
        Mockito.when(argentina.getId()).thenReturn("AR");

        List<Provincia> provincias = Localizacion.servicio().obtenerProvincias(argentina);
        Assert.assertTrue(!provincias.isEmpty());
    }

    @Test
    public void ciudades() {
        Provincia buenosAiresInterior = Mockito.mock(Provincia.class);
        Mockito.when(buenosAiresInterior.getId()).thenReturn("TUxBUFpPTmFpbnRl"); // Buenos Aires Interior

        List<Ciudad> ciudades = Localizacion.servicio().obtenerCiudades(buenosAiresInterior);
        Assert.assertTrue(!ciudades.isEmpty());
    }

    @Test
    public void monedas() {
        List<Moneda> monedas = Localizacion.servicio().obtenerMonedas();
        Assert.assertTrue(!monedas.isEmpty());
    }

    @Test
    public void moneda() {
        Pais argentina = Mockito.mock(Pais.class);
        Mockito.when(argentina.getId()).thenReturn("AR");

        Moneda pesosArg = Localizacion.servicio().obtenerMoneda(argentina);
        Assert.assertEquals("Peso argentino", pesosArg.getDescripcion());
    }
}
