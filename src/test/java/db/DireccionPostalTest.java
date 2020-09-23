package db;

import org.junit.*;
import org.mockito.Mockito;
import Localizacion.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class DireccionPostalTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @BeforeClass
    public static void inicializarTest() {
        ServicioMercadoLibre servicio = Mockito.spy(new ServicioMercadoLibre());
        Localizacion.setServicio(servicio);

        Mockito.doReturn(
            "{\"id\": \"idChivilcoy\", \"name\": \"Chivilcoy\",\n" +
                    "\"state\": {\"id\": \"TUxBUFpPTmFpbnRl\", \"name\": \"Buenos Aires Interior\"},\n" +
                    "\"country\": {\"id\": \"AR\", \"name\": \"Argentina\"},\n" +
                    "\"neighborhoods\": [\n" +
                    "{\"id\": \"TUxBQkNISTI2MTBa\", \"name\": \"Chivilcoy\"},\n" +
                    "{\"id\": \"TUxBQkVNSTg2MzNa\", \"name\": \"Emilio Ayarza\"}\n" +
                    "]}"
        ).when(servicio).obtenerStringJSON("classified_locations/cities/idChivilcoy");

        Ciudad ciudadTest = new Ciudad("idChivilcoy", "-");
        DireccionPostal direccionTest = new DireccionPostal("calle0", "1", 2, ciudadTest);

        EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(direccionTest);
        transaction.commit();
        entityManager.clear();
    }

    @Test
    public void codigoPostalPersisteIdCiudad() {
        DireccionPostal resultado = (DireccionPostal)entityManager().createQuery("from DireccionPostal").getResultList().get(0);
        Assert.assertEquals("idChivilcoy", resultado.getCiudad().getIdAPI());
    }

    @Test
    public void codigoPostalPersisteCiudad() {
        DireccionPostal resultado = (DireccionPostal)entityManager().createQuery("from DireccionPostal").getResultList().get(0);
        Assert.assertEquals("Chivilcoy", resultado.getCiudad().getNombre());
    }

    @Test
    public void codigoPostalPersisteProvincia() {
        List<DireccionPostal> resultados = entityManager().createQuery("from DireccionPostal").getResultList();
        Assert.assertEquals("TUxBUFpPTmFpbnRl", resultados.get(0).getCiudad().getProvincia().getIdAPI());
        Assert.assertEquals("Buenos Aires Interior", resultados.get(0).getCiudad().getProvincia().getNombre());
    }

    @Test
    public void codigoPostalPersistePais() {
        List<DireccionPostal> resultados = entityManager().createQuery("from DireccionPostal").getResultList();
        Assert.assertEquals("AR", resultados.get(0).getCiudad().getProvincia().getPais().getIdAPI());
        Assert.assertEquals("Argentina", resultados.get(0).getCiudad().getProvincia().getPais().getNombre());
    }
}
