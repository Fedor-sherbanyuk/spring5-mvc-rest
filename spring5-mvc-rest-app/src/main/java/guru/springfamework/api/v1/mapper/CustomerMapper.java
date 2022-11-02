package guru.springfamework.api.v1.mapper;


import guru.springfamework.api.v1.domain.Customer;

import guru.springframework.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by jt on 9/25/17.
 */
@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    //    @Mapping(source = "id", target = "id")
    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerToCustomerDTOCustomer(CustomerDTO customerDTO);
}