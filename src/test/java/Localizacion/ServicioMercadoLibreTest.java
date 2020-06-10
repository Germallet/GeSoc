package Localizacion;

import Localizacion.MercadoLibre.MercadoLibreAPI;
import org.junit.*;
import java.util.List;

public class MercadoLibreAPITest {
    @Test
    public void paises() {
        List<Pais> paises = MercadoLibreAPI.servicio().obtenerPaises();
        paises.forEach(pais -> System.out.println(pais.getNombre()));
        Assert.assertTrue(!paises.isEmpty());
    }

    @Test
    public void provincias() {
        List<Pais> paises = MercadoLibreAPI.servicio().obtenerPaises();
        List<Provincia> provincias = paises.get(0).provincias();
        provincias.forEach(provincia -> System.out.println(provincia.getNombre()));
        Assert.assertTrue(!provincias.isEmpty());
    }

    @Test
    public void ciudades() {
        List<Pais> paises = MercadoLibreAPI.servicio().obtenerPaises();
        List<Provincia> provincias = paises.get(0).provincias();
        List<Ciudad> ciudades = provincias.get(7).ciudades();
        ciudades.forEach(ciudad -> System.out.println(ciudad.getNombre()));
        Assert.assertTrue(!ciudades.isEmpty());
    }

    /*@Test
    public void paises() {
        List<PaisAPI> paises = MercadoLibreAPI.servicio().obtenerPaises();
        Assert.assertTrue(!paises.isEmpty());
    }

    @Test
    public void paisAR() {
        PaisAPI pais = MercadoLibreAPI.servicio().obtenerPaisAPI("AR");
        Assert.assertEquals("Argentina", pais.name);
    }

    @Test
    public void ciudades() {
        PaisAPI pais = MercadoLibreAPI.servicio().obtenerPaisAPI("AR");
        ProvinciaAPI provincia = MercadoLibreAPI.servicio().obtenerProvinciaAPI(pais.states.get(0).id);
        Assert.assertTrue(!provincia.cities.isEmpty());
    }

    @Test
    public void completo() {
            List<PaisAPI> paises = MercadoLibreAPI.servicio().obtenerPaises();
        paises.forEach(paisAPI -> {
            paisAPI = MercadoLibreAPI.servicio().obtenerPaisAPI(paisAPI.id);
            /*System.out.println(paisAPI.name);

            paisAPI.states.forEach(provinciaAPI -> {
                //provinciaAPI = MercadoLibreAPI.servicio().provincia(provinciaAPI.id);
                System.out.println("    " + provinciaAPI.name);

                //provinciaAPI.cities.forEach(ciudadAPI -> System.out.println("       " + ciudadAPI.name));
            });*/
        //});
    //}*/
}
