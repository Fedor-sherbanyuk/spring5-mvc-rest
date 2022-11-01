package guru.springfamework.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jt on 9/24/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {
//    private Long id;

    @ApiModelProperty(value = "This is the name", required = true)
    private String name;
    @ApiModelProperty(value = "This is the vendorUrl", required = true)
    private String vendorUrl;
}
