package service.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Customer> index() {
		return customerService.getAll();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void createCustomer (@RequestBody Customer customer)
	{
		customerService.addCustomer(customer);
	}

	private CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
}
