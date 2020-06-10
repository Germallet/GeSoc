package Localizacion.MercadoLibre;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import Localizacion.Ciudad;
import Localizacion.Pais;
import Localizacion.Provincia;
import Localizacion.ServicioLocalizacion;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class MercadoLibreAPI implements ServicioLocalizacion {
    private static MercadoLibreAPI instancia = null;
    private Retrofit retrofit;

    private MercadoLibreAPI() {
        this.retrofit = new Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    public static MercadoLibreAPI servicio() {
        if (instancia == null)
            instancia = new MercadoLibreAPI();
        return instancia;
    }

    private <T> T ejecutarRequest(Call<T> request) {
        try {
            return request.execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<PaisAPI> obtenerPaisesAPI() {
        MercadoLibreInterfazAPI georefService = this.retrofit.create(MercadoLibreInterfazAPI.class);
        Call<List<PaisAPI>> request = georefService.paises();
        return ejecutarRequest(request);
    }
    private PaisAPI obtenerPaisAPI(String idPais) {
        MercadoLibreInterfazAPI georefService = this.retrofit.create(MercadoLibreInterfazAPI.class);
        Call<PaisAPI> request = georefService.pais(idPais);
        return ejecutarRequest(request);
    }
    private PaisAPI obtenerPaisAPI_PorNombre(String nombre) {
        List<PaisAPI> paises = obtenerPaisesAPI();
        return paises.stream().filter(candidato -> candidato.name.equals(nombre)).findFirst().orElse(null);
    }
    private ProvinciaAPI obtenerProvinciaAPI(String idProvincia) {
        MercadoLibreInterfazAPI georefService = this.retrofit.create(MercadoLibreInterfazAPI.class);
        Call<ProvinciaAPI> request = georefService.provincia(idProvincia);
        return ejecutarRequest(request);
    }
    private MonedaAPI obtenerMonedaAPI(String idMoneda) {
        MercadoLibreInterfazAPI georefService = this.retrofit.create(MercadoLibreInterfazAPI.class);
        Call<MonedaAPI> request = georefService.moneda(idMoneda);
        return ejecutarRequest(request);
    }

    @Override
    public List<Pais> obtenerPaises() {
        return obtenerPaisesAPI().stream().map(paisAPI -> paisAPI.generarPais()).collect(Collectors.toList());
    }
    @Override
    public List<Provincia> obtenerProvincias(Pais pais) {
        PaisAPI paisAPI = obtenerPaisAPI_PorNombre(pais.getNombre());
        List<ProvinciaAPI> provinciasAPI = obtenerPaisAPI(paisAPI.id).states;
        return provinciasAPI.stream().map(provinciaAPI -> provinciaAPI.generarProvincia(pais)).collect(Collectors.toList());
    }
    @Override
    public List<Ciudad> obtenerCiudades(Provincia provincia) {
        PaisAPI paisAPI = obtenerPaisAPI_PorNombre(provincia.getPais().getNombre());
        List<ProvinciaAPI> provinciasAPI = obtenerPaisAPI(paisAPI.id).states;
        ProvinciaAPI provinciaAPI = provinciasAPI.stream().filter(candidato -> candidato.name.equals(provincia.getNombre())).findFirst().orElse(null);
        ProvinciaAPI provinciaAPICompleta = obtenerProvinciaAPI(provinciaAPI.id);
        return provinciaAPICompleta.cities.stream().map(ciudadAPI -> ciudadAPI.generarCiudad()).collect(Collectors.toList());
    }
}
