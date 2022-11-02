package guru.springfamework.api.v1.services;

import guru.springfamework.api.v1.controllers.CategoryController;
import guru.springfamework.api.v1.domain.Customer;
import guru.springfamework.api.v1.mapper.CustomerMapper;

import guru.springfamework.api.v1.repositories.CustomerRepository;
import guru.springframework.model.CustomerDTO;
import org.springframework.stereotype.Service;

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
                            customerDTO.setCustomerUrl(CategoryController.BASE_URL+ customer.getId());
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
        return customerRepository.findById(id).map(customerMapper::customerToCustomerDTO).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createdNewCustomer(CustomerDTO customerDTO) {
        Customer customerNew =customerMapper.customerToCustomerDTOCustomer(customerDTO);
        Customer customersave =customerRepository.save(customerNew);
        CustomerDTO customerNewsave=customerMapper.customerToCustomerDTO(customersave);
        customerNewsave.setCustomerUrl(CategoryController.BASE_URL+customersave.getId());
        return customerNewsave;
    }

    private CustomerDTO saveAndReturn(Customer customer){
        Customer saveCustomer =customerRepository.save(customer);
        CustomerDTO customerDTO=customerMapper.customerToCustomerDTO(saveCustomer);
        customerDTO.setCustomerUrl(CategoryController.BASE_URL+saveCustomer.getId());
        return customerDTO;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer =customerMapper.customerToCustomerDTOCustomer(customerDTO);
        customer.setId(id);
        return saveAndReturn(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {

            if(customerDTO.getFirstname() != null){
                customer.setFirstname(customerDTO.getFirstname());
            }

            if(customerDTO.getLastname() != null){
                customer.setLastname(customerDTO.getLastname());
            }
            CustomerDTO customerDTO1=customerMapper.customerToCustomerDTO(customerRepository.save(customer));
            customerDTO1.setCustomerUrl(CategoryController.BASE_URL+id);

            return customerDTO1;
        }).orElseThrow(ResourceNotFoundException::new); //todo implement better exception handling;
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);

    }

    public class Fedor {
        public String getJob(String resume) {
            if (resume.equalsIgnoreCase("Good"))
                return "Call Fedor 07331256660";
            else
                return "Fedor we call later";
        }
    }
}
