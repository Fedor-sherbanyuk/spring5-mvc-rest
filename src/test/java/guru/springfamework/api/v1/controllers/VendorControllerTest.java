package guru.springfamework.api.v1.controllers;

import guru.springfamework.api.v1.AbstractRestControllerTest;
import guru.springfamework.api.v1.domain.VendorListDTO;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static guru.springfamework.api.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//public class VendorControllerTest extends AbstractRestControllerTest {
//    public static final String NAME = "Joe";
//
//    @Mock
//    VendorService vendorService;
//
//    @InjectMocks
//    VendorController vendorController;
//
//    MockMvc mockMvc;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
//                .setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
//    }
//
//    @Test
//    public void testGetVendors() throws Exception {
//        VendorDTO vendorDTO = new VendorDTO();
//        vendorDTO.setName(NAME);
//
//        VendorDTO vendorDTO1 = new VendorDTO();
////        customerDTO2.setId(2l);
//        vendorDTO1.setName("JIIMI");
//
//        List<VendorDTO> vendorDTOList = Arrays.asList(vendorDTO, vendorDTO1);
//
//        when(vendorService.getAllVendors()).thenReturn(vendorDTOList);
//
//        mockMvc.perform(get(CategoryController.BASE_URL_VENDOR)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.vendorDTOList", hasSize(2)));
//    }
//
//    @Test
//    public void testGetVendorById() throws Exception {
//        VendorDTO vendorDTO = new VendorDTO();
//        vendorDTO.setName(NAME);
//        vendorDTO.setVendorUrl(CategoryController.BASE_URL_VENDOR + 1L);
//
//        when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO);
//
//        mockMvc.perform(get(CategoryController.BASE_URL_VENDOR + 1L)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", equalTo(NAME)));
//    }
//
//    @Test
//    public void createNewVendor() throws Exception {
//        //given
//        VendorDTO vendorDTO = new VendorDTO();
//        vendorDTO.setName("Fred");
//
//        VendorDTO returnDTO = new VendorDTO();
//        returnDTO.setName(vendorDTO.getName());
//        returnDTO.setVendorUrl(CategoryController.BASE_URL_VENDOR + 1L);
//
//        when(vendorService.createdNewVendor(vendorDTO)).thenReturn(returnDTO);
//
//        //when/then
//        mockMvc.perform(post(CategoryController.BASE_URL_VENDOR)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(vendorDTO)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name", equalTo("Fred")))
//                .andExpect(jsonPath("$.vendorUrl", equalTo(CategoryController.BASE_URL_VENDOR + 1L)));
//    }
//
//    @Test
//    public void updateNewVendor() throws Exception {
//        //given
//        VendorDTO vendorDTO = new VendorDTO();
//        vendorDTO.setName("Fred");
//
//        VendorDTO returnDTO = new VendorDTO();
//        returnDTO.setName(vendorDTO.getName());
//        returnDTO.setVendorUrl(CategoryController.BASE_URL_VENDOR + 1L);
//
//        when(vendorService.saveVendorByDTO(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);
//
//        //when/then
//        mockMvc.perform(put(CategoryController.BASE_URL_VENDOR + 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(vendorDTO)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", equalTo("Fred")))
//                .andExpect(jsonPath("$.vendorUrl", equalTo(CategoryController.BASE_URL_VENDOR + 1L)));
//    }
//
//    @Test
//    public void patchNewVendor() throws Exception {
//        //given
//        VendorDTO vendorDTO = new VendorDTO();
//        vendorDTO.setName(NAME);
//
//        VendorDTO returnDTO = new VendorDTO();
//        returnDTO.setName(vendorDTO.getName());
//        returnDTO.setVendorUrl(CategoryController.BASE_URL_VENDOR + 1L);
//
//        when(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO);
//
//        //when/then
//        mockMvc.perform(patch(CategoryController.BASE_URL_VENDOR + 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(vendorDTO)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", equalTo(NAME)))
//                .andExpect(jsonPath("$.vendorUrl", equalTo(CategoryController.BASE_URL_VENDOR + 1L)));
//    }
//
//    @Test
//    public void deleteVendorById() throws Exception {
//        mockMvc.perform(delete(CategoryController.BASE_URL_VENDOR + 1L)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//        verify(vendorService).deleteVendorById(anyLong());
//
//    }
//}
import guru.springfamework.api.v1.model.VendorDTO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {VendorController.class})
public class VendorControllerTest {

    @MockBean //provided by Spring Context
    VendorService vendorService;

    @Autowired
    MockMvc mockMvc; //provided by Spring Context

    VendorDTO vendorDTO_1;
    VendorDTO vendorDTO_2;

    @Before
    public void setUp() throws Exception {
        vendorDTO_1 = new VendorDTO("Vendor 1", CategoryController.BASE_URL_VENDOR + "1");
        vendorDTO_2 = new VendorDTO("Vendor 2", CategoryController.BASE_URL_VENDOR + "2");
    }

    @Test
    public void getVendorList() throws Exception {
        List<VendorDTO> vendorListDTO =  Arrays.asList(vendorDTO_1, vendorDTO_2);

        given(vendorService.getAllVendors()).willReturn(vendorListDTO);

        mockMvc.perform(get(CategoryController.BASE_URL_VENDOR)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendorDTOList", hasSize(2)));
    }

    @Test
    public void getVendorById() throws Exception {

        given(vendorService.getVendorById(anyLong())).willReturn(vendorDTO_1);

        mockMvc.perform(get(CategoryController.BASE_URL_VENDOR + "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void createNewVendor() throws Exception {

        given(vendorService.createdNewVendor(vendorDTO_1)).willReturn(vendorDTO_1);

        mockMvc.perform(post(CategoryController.BASE_URL_VENDOR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO_1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void updateVendor() throws Exception {

        given(vendorService.saveVendorByDTO(anyLong(), any(VendorDTO.class))).willReturn(vendorDTO_1);

        mockMvc.perform(put(CategoryController.BASE_URL_VENDOR + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void patchVendor() throws Exception {
        given(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).willReturn(vendorDTO_1);

        mockMvc.perform(patch(CategoryController.BASE_URL_VENDOR + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }


    @Test
    public void deleteVendor() throws Exception {
        mockMvc.perform(delete(CategoryController.BASE_URL_VENDOR + "1"))
                .andExpect(status().isOk());

        then(vendorService).should().deleteVendorById(anyLong());

    }
}