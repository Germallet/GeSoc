package Localizacion.MercadoLibre;

import Localizacion.MercadoLibre.MonedaAPI;
import Localizacion.MercadoLibre.PaisAPI;
import Localizacion.MercadoLibre.ProvinciaAPI;
import retrofit2.*;
import retrofit2.http.*;
import java.util.*;

public interface MercadoLibreInterfazAPI {
    @GET("classified_locations/countries")
    Call<List<PaisAPI>> paises();

    @GET("classified_locations/countries/{idPais}")
    Call<PaisAPI> pais(@Path(value="idPais", encoded=false) String idPais);

    @GET("classified_locations/states/{idProvincia}")
    Call<ProvinciaAPI> provincia(@Path(value="idProvincia", encoded=false) String idProvincia);

    @GET("currencies/{idMoneda}")
    Call<MonedaAPI> moneda(@Path(value="idMoneda", encoded=false) String idMoneda);
}
