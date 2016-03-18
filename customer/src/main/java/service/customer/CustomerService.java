package service.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.customer.repo.CustomerRepository;

@Service
public class CustomerService {
	private CustomerRepository customerRepository;

	public List<Customer> getAll() {

		List<Customer> customers = new ArrayList<>();

		for (Customer c : customerRepository.findAll()) {
			customers.add(c);
		}

		return customers;
	}

	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
}
