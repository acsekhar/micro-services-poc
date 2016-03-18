package service.customer.repo;

import org.springframework.data.cassandra.repository.CassandraRepository;

import service.customer.Customer;

public interface CustomerRepository extends CassandraRepository<Customer> {

}
