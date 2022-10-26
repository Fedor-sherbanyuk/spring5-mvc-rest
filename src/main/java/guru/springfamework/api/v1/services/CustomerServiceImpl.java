package guru.springfamework.api.v1.services;

import guru.springfamework.api.v1.domain.Customer;
import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                        .map(customer -> {
                            CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                            customerDTO.setCustomerUrl("/api/v1/customers/"+ customer.getId());
                            return customerDTO;
                        })
              .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerByFirstName(String name) {

        return customerMapper.customerToCustomerDTO(customerRepository.findByFirstname(name));
    }

    @Override
    public CustomerDTO getCustomerByLastName(String name) {
        return customerMapper.customerToCustomerDTO(customerRepository.findByLastname(name));
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id).map(customerMapper::customerToCustomerDTO).orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO createdNewCustomer(CustomerDTO customerDTO) {
        Customer customerNew =customerMapper.customerToCustomerDTOCustomer(customerDTO);
        Customer customersave =customerRepository.save(customerNew);
        CustomerDTO customerNewsave=customerMapper.customerToCustomerDTO(customersave);
        customerNewsave.setCustomerUrl("/api/v1/customers/"+customersave.getId());
        return customerNewsave;
    }
}
