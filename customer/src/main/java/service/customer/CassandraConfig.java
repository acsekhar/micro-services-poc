package service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.config.CassandraCqlClusterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = { "service.customer.repo" })
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public CassandraCqlClusterFactoryBean cluster() {
		CassandraCqlClusterFactoryBean cluster = super.cluster();
		cluster.setContactPoints(env.getRequiredProperty("cassandra.contactpoints"));
		cluster.setPort(Integer.parseInt(env.getRequiredProperty("cassandra.port")));
		return cluster;
	}

	@Override
	protected String getKeyspaceName() {
		return env.getProperty("cassandra.keyspace");
	}
}