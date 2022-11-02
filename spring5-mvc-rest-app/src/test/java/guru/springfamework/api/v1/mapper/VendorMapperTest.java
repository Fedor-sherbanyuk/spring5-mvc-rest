package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.domain.Vendor;
import guru.springfamework.api.v1.model.VendorDTO;
import junit.framework.TestCase;
import org.junit.Test;


public class VendorMapperTest extends TestCase {
    public static final String NAME = "Joe";

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void testCustomerToCustomerDTO() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(NAME, vendorDTO.getName());
    }

    @Test
    public void testCustomerToCustomerDTOCustomer() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(vendor.getName(), vendorDTO.getName());
    }
}



