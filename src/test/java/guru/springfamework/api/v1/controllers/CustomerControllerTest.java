package guru.springfamework.api.v1.controllers;

import guru.springfamework.api.v1.AbstractRestControllerTest;
import guru.springfamework.api.v1.model.CustomerDTO;

import guru.springfamework.api.v1.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest  extends AbstractRestControllerTest {
    public static final String NAME = "Joe";
    public static final String LASTNAME = "CuCu";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testGetCustomers() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setId(1l);
        customerDTO.setFirstname(NAME);
        customerDTO.setLastname(LASTNAME);

        CustomerDTO customerDTO2 = new CustomerDTO();
//        customerDTO2.setId(2l);
        customerDTO2.setFirstname("JIIMI");
        customerDTO2.setLastname("South_Park");

        List<CustomerDTO> customerDTOList = Arrays.asList(customerDTO, customerDTO2);

        when(customerService.getAllCustomers()).thenReturn(customerDTOList);

        mockMvc.perform(get("/api/v1/customers/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerDTOList", hasSize(2)));
    }

//    @Test
//    public void testGetCustomerByFirstName() throws Exception {
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setId(1l);
//        customerDTO.setFirstname(NAME);
//        customerDTO.setLastname(LASTNAME);
//
//        when(customerService.getCustomerByFirstName(anyString())).thenReturn(customerDTO);
//
//        mockMvc.perform(get("/api/v1/customeries/s{Joe}")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", equalTo(NAME)));
//    }

//    @Test
//    public void testGetCustomerByLastName() throws Exception {
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setId(1l);
//        customerDTO.setFirstname(NAME);
//        customerDTO.setLastname(LASTNAME);
//
//        when(customerService.getCustomerByLastName(anyString())).thenReturn(customerDTO);
//
//        mockMvc.perform(get("/api/v1/customers/CuCu")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.lastname", equalTo(LASTNAME)));
//    }

    @Test
    public void testGetCustomerById() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setId(1l);
        customerDTO.setFirstname(NAME);
        customerDTO.setLastname(LASTNAME);
        customerDTO.setCustomerUrl("/api/v1/customers/1");

        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        mockMvc.perform(get("/api/v1/customers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(NAME)));
    }

    @Test
    public void createNewCustomer() throws Exception {
        //given
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname("Fred");
        customer.setLastname("Flintstone");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstname(customer.getFirstname());
        returnDTO.setLastname(customer.getLastname());
        returnDTO.setCustomerUrl("/api/v1/customers/1");

        when(customerService.createdNewCustomer(customer)).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(post("/api/v1/customers/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo("Fred")))
                .andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")));
    }

}
