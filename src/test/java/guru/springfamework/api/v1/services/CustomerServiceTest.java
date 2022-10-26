package guru.springfamework.api.v1.services;

import guru.springfamework.api.v1.domain.Category;
import guru.springfamework.api.v1.domain.Customer;
import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.repositories.CategoryRepository;
import guru.springfamework.api.v1.repositories.CustomerRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class CustomerServiceTest extends TestCase{
    public static final Long ID = 1L;

    public static final String NAME = "Joe";
    public static final String LASTNAME = "CuCu";

    @Mock
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

@Test
    public void testGetAllCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        //when
        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();

        //then
        assertEquals(3, customerDTOList.size());
    }

//    public void testGetCustomerByFirstName() {
//        Customer customer = new Customer();
////        customer.setId(ID);
//        customer.setFirstname(NAME);
//        customer.setLastname(LASTNAME);
//
//        when(customerRepository.findByFirstname(anyString())).thenReturn(customer);
//
//        //when
//        CustomerDTO customerDTO = customerService.getCustomerByFirstName(NAME);
//
//        //then
////        assertEquals(ID, customerDTO.getId());
//        assertEquals(NAME, customerDTO.getFirstname());
//    }

//    public void testGetCustomerByLastName() {
//        Customer customer = new Customer();
////        customer.setId(ID);
//        customer.setFirstname(NAME);
//        customer.setLastname(LASTNAME);
//
//        when(customerRepository.findByLastname(anyString())).thenReturn(customer);
//
//        //when
//        CustomerDTO customerDTO = customerService.getCustomerByLastName(LASTNAME);
//
//        //then
////        assertEquals(ID, customerDTO.getId());
//        assertEquals(LASTNAME, customerDTO.getLastname());
//    }
    @Test
    public void testGetCustomerById() {
        Customer customer = new Customer();
//        customer.setId(ID);
        customer.setFirstname(NAME);
        customer.setLastname(LASTNAME);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(ID);

        //then
        assertEquals(NAME, customerDTO.getFirstname());
//        assertEquals(LASTNAME, customerDTO.getLastname());
    }

    @Test
    public void testCreatedNewCustomer() {
        CustomerDTO customerDTO =new CustomerDTO();
        customerDTO.setFirstname(NAME);

        Customer customer = new Customer();
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());
        customer.setId(ID);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        //when
        CustomerDTO customerDTONew = customerService.createdNewCustomer(customerDTO);

        //then
        assertEquals(customerDTONew.getFirstname(), customer.getFirstname());
        assertEquals("/api/v1/customers/1", customerDTONew.getCustomerUrl());
//        assertEquals(LASTNAME, customerDTO.getLastname());
    }
}
