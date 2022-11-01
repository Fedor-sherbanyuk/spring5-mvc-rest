package guru.springfamework.api.v1.services;

import guru.springfamework.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerByFirstName(String name);

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createdNewCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerByLastName(String name);
    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);
    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
    void deleteCustomerById(Long id);
}
