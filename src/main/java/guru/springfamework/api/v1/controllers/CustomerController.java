package guru.springfamework.api.v1.controllers;

import guru.springfamework.api.v1.domain.CustomerListDTO;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getCustomers() {
        CustomerListDTO customerListDTO = new CustomerListDTO(customerService.getAllCustomers());
        return new ResponseEntity<CustomerListDTO>(customerListDTO, HttpStatus.OK);
    }

//    @GetMapping("/s{name}")
//    public ResponseEntity<CustomerDTO> getCustomerByFirstName(@PathVariable String firstname) {
//        CustomerDTO customerDTO = customerService.getCustomerByFirstName(firstname);
//        return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
//    }

//    @GetMapping("{lastname}")
//    public ResponseEntity<CustomerDTO> getCustomerByLastName(@PathVariable String lastname) {
//        CustomerDTO customerDTO = customerService.getCustomerByLastName(lastname);
//        return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerByLastName(@PathVariable Long id) {
        CustomerDTO customerDTO = customerService.getCustomerById(id);
        return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.createdNewCustomer(customerDTO),
                HttpStatus.CREATED);
    }
}
