package Persistence;

import org.uqbarproject.jpa.java8.extras.WithEntityManager;
import javax.persistence.EntityManager;

public interface WithGlobalEntityManagerEnv extends WithEntityManager {
    default EntityManager entityManager() {
        return PerThreadEntityManagersEnv.getEntityManager();
    }
}
