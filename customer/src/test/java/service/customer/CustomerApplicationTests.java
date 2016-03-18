package service.customer;

import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionIntegrationTestExecutionListener;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.CassandraUnitTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
	@ContextConfiguration(classes = TestCassandraConfig.class),
	@ContextConfiguration(classes = CustomerApplication.class),
	@ContextConfiguration(classes = CassandraConfig.class) })
@TestExecutionListeners(listeners = { CassandraUnitDependencyInjectionIntegrationTestExecutionListener.class,
		CassandraUnitDependencyInjectionTestExecutionListener.class, CassandraUnitTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class }, mergeMode = MergeMode.MERGE_WITH_DEFAULTS)

@CassandraDataSet(value = "cql/dataset1.cql", keyspace = "services")
@EmbeddedCassandra
public class CustomerApplicationTests {

	@Test
	public void contextLoads() {
	}

}
