package db;

import static org.junit.Assert.*;

import Persistence.WithGlobalEntityManagerEnv;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManagerEnv {

	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		withTransaction(() -> {});
	}

}
