package guru.springfamework.api.v1.controllers;

import guru.springfamework.api.v1.domain.CustomerListDTO;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(description = "This is my Customer Controller")
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "This will get a list of customerListDTO.", notes = "There are some notes about the API.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getCustomers() {
        CustomerListDTO customerListDTO = new CustomerListDTO(customerService.getAllCustomers());
        return customerListDTO;
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerByLastName(@PathVariable Long id) {
        CustomerDTO customerDTO = customerService.getCustomerById(id);
        return customerDTO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.createdNewCustomer(customerDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateNewCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO){
        return customerService.saveCustomerByDTO(id,customerDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchNewCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO){
        return customerService.patchCustomer(id,customerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomerById(@PathVariable Long id){
        customerService.deleteCustomerById(id);
    }
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
