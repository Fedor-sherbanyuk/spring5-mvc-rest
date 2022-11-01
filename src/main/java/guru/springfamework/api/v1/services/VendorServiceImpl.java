package guru.springfamework.api.v1.services;

import guru.springfamework.api.v1.controllers.CategoryController;
import guru.springfamework.api.v1.domain.Vendor;
import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(CategoryController.BASE_URL_VENDOR + vendor.getId());
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorByName(String name) {

        return vendorMapper.vendorToVendorDTO(vendorRepository.findByname(name));
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createdNewVendor(VendorDTO vendorDTO) {
        Vendor vendorNew = vendorMapper.vendorToVendorDTOVendor(vendorDTO);
        Vendor vendorSave = vendorRepository.save(vendorNew);
        VendorDTO vendorNewsave = vendorMapper.vendorToVendorDTO(vendorSave);
        vendorNewsave.setVendorUrl(CategoryController.BASE_URL_VENDOR + vendorSave.getId());
        return vendorNewsave;
    }

    private VendorDTO saveAndReturn(Vendor vendor) {
        Vendor vendorSave = vendorRepository.save(vendor);
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendorSave);
        vendorDTO.setVendorUrl(CategoryController.BASE_URL_VENDOR + vendorSave.getId());
        return vendorDTO;
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorToVendorDTOVendor(vendorDTO);
        vendor.setId(id);
        return saveAndReturn(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {

            if (vendorDTO.getName() != null) {
                vendor.setName(vendorDTO.getName());
            }
            VendorDTO vendorDTO1 = vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
            vendorDTO1.setVendorUrl(CategoryController.BASE_URL_VENDOR + id);

            return vendorDTO1;
        }).orElseThrow(ResourceNotFoundException::new); //todo implement better exception handling;
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);

    }

//    public class Fedor {
//        public String getJob(String resume) {
//            if (resume.equalsIgnoreCase("Good"))
//                return "Call Fedor 07331256660";
//            else
//                return "Fedor we call later";
//        }
//    }
}
