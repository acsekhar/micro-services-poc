package service.customer;

import org.springframework.cassandra.config.CassandraCqlClusterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestCassandraConfig extends CassandraConfig {

	@Bean
	public CassandraCqlClusterFactoryBean cluster() {
		
		System.setProperty("cassandra.contactpoints","127.0.0.1");
		System.setProperty("cassandra.port", "9142");
		
		return super.cluster();
		//cluster.setContactPoints("127.0.0.1");
		//cluster.setPort(9142);
		//return cluster;
	}
}
