package guru.springfamework.api.v1.domain;

import guru.springfamework.api.v1.model.VendorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by jt on 9/26/17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorListDTO {
    List<VendorDTO> vendorDTOList;
}
