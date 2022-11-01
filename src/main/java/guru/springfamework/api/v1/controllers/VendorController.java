package guru.springfamework.api.v1.controllers;

import guru.springfamework.api.v1.domain.VendorListDTO;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "This is my Vendor Controller")
@RestController
@RequestMapping(CategoryController.BASE_URL_VENDOR)
public class VendorController {
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "This will get a list of vendorListDTO.", notes = "There are some notes about the API.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getVendors() {
        VendorListDTO vendorListDTO = new VendorListDTO(vendorService.getAllVendors());
        return vendorListDTO;
    }

    @ApiOperation(value = "This will createdNewVendor VendorDTO.", notes = "There are some notes about the API.")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getCustomerByName(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @ApiOperation(value = "This will createdNewVendor VendorDTO.", notes = "There are some notes about the API.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.createdNewVendor(vendorDTO);
    }

    @ApiOperation(value = "This will updateNewVendor VendorDTO.", notes = "There are some notes about the API.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateNewVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.saveVendorByDTO(id, vendorDTO);
    }

    @ApiOperation(value = "This will patchNewVendor VendorDTO.", notes = "There are some notes about the API.")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchNewVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patchVendor(id, vendorDTO);
    }

    @ApiOperation(value = "This will deleteVendorById.", notes = "There are some notes about the API.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendorById(@PathVariable Long id) {
        vendorService.deleteVendorById(id);
    }
}
