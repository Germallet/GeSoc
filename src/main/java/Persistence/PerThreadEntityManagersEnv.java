package Persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class PerThreadEntityManagersEnv {
    private static EntityManagerFactory emf;
    private static ThreadLocal<EntityManager> threadLocal;

    public PerThreadEntityManagersEnv() {
    }

    public static EntityManager getEntityManager() {
        EntityManager manager = threadLocal.get();
        if (manager == null || !manager.isOpen()) {
            manager = emf.createEntityManager();
            threadLocal.set(manager);
        }

        return manager;
    }

    public static void closeEntityManager() {
        EntityManager em = threadLocal.get();
        threadLocal.set(null);
        em.close();
    }

    private static Map<String, Object> getOverrides() {
        Map<String, Object> configOverrides = new HashMap<>();
        if (System.getenv("DATABASE_URL") != null)
            configOverrides.put("hibernate.connection.url", "jdbc:" + System.getenv("DATABASE_URL"));
        System.out.println("------------------------------------------->");
        System.out.println(System.getenv("DATABASE_URL"));
        System.out.println("-------------------------------------------");
        return configOverrides;
    }

    static {
        try {
            emf = Persistence.createEntityManagerFactory("db", getOverrides());
            threadLocal = new ThreadLocal();
        } catch (Exception var1) {
            var1.printStackTrace();
        }

    }
}
