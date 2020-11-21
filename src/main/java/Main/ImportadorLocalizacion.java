package Main;

import java.util.List;
import Localizacion.*;
import javax.persistence.*;
import Persistence.PerThreadEntityManagersEnv;

public class ImportadorLocalizacion extends PerThreadEntityManagersEnv {
    public static void main(String[] args) {
        System.out.println("Importando localización de Mercado Libre");

        List<Pais> paises = Localizacion.servicio().obtenerPaises();
        Pais argentina = paises.stream().filter(pais -> pais.getIdAPI().equals("AR")).findFirst().get();

        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        paises.forEach(pais -> entityManager.persist(pais) );
        argentina.provincias().forEach(provincia -> {
            entityManager.persist(provincia);
            provincia.ciudades().forEach(ciudad -> entityManager.persist(ciudad));
        });

        transaction.commit();
        closeEntityManager();
    }
}
