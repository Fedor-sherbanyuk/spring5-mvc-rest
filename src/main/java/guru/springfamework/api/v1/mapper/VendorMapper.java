package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.domain.Vendor;
import guru.springfamework.api.v1.model.VendorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by jt on 9/25/17.
 */
@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    //    @Mapping(source = "id", target = "id")
    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorToVendorDTOVendor(VendorDTO vendorDTO);
}