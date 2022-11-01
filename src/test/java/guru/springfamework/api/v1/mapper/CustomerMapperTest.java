package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.domain.Customer;
import guru.springfamework.api.v1.model.CustomerDTO;
import junit.framework.TestCase;
import org.junit.Test;


public class CustomerMapperTest extends TestCase {
    public static final String NAME = "Joe";
    public static final String LASTNAME = "CuCu";
//    public static final long ID = 1L;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void testCustomerToCustomerDTO() {
        Customer customer = new Customer();
        customer.setFirstname(NAME);
        customer.setLastname(LASTNAME);
//        customer.setId(ID);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

//        assertEquals(Long.valueOf(ID), customerDTO.getId());
        assertEquals(NAME, customerDTO.getFirstname());
        assertEquals(LASTNAME, customerDTO.getLastname());
    }
}



