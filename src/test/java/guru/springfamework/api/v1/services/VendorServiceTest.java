package guru.springfamework.api.v1.services;

import guru.springfamework.api.v1.controllers.CategoryController;
import guru.springfamework.api.v1.domain.Vendor;
import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.repositories.VendorRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class VendorServiceTest {
    public static final Long ID = 1L;

    public static final String NAME = "Joe";

    @Mock
    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void testGetAllVendors() {
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);

        //when
        List<VendorDTO> vendorDTOList = vendorService.getAllVendors();
        then(vendorRepository).should(times(1)).findAll();

        //then
        Assert.assertThat(vendorDTOList.size(), is(equalTo(3)));
    }

    @Test
    public void testGetVendorById() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));

        //when
        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        then(vendorRepository).should(times(1)).findById(anyLong());

        //then
        Assert.assertEquals(NAME, vendorDTO.getName());
    }

    @Test
    public void testCreatedNewVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor vendor = new Vendor();
        vendor.setName(vendorDTO.getName());
        vendor.setId(ID);
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        //when
        VendorDTO vendorDTO1 = vendorService.createdNewVendor(vendorDTO);
        then(vendorRepository).should().save(any(Vendor.class));

        //then
        Assert.assertEquals(vendorDTO1.getName(), vendor.getName());
        Assert.assertThat(vendorDTO1.getVendorUrl(),containsString("1"));
    }

    @Test
    public void saveCustomerByDTO() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor vendor = new Vendor();
        vendor.setName(vendorDTO.getName());
        vendor.setId(ID);
        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        //when
        VendorDTO vendorDTO1 = vendorService.saveVendorByDTO(ID, vendorDTO);

        //then
        Assert.assertEquals(vendorDTO1.getName(), vendor.getName());
        Assert.assertEquals(CategoryController.BASE_URL_VENDOR + 1L, vendorDTO1.getVendorUrl());
    }

    @Test
    public void deleteCustomerById() {
        vendorRepository.deleteById(ID);
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}
