package guru.springfamework.api.v1.domain;

import guru.springfamework.api.v1.model.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by jt on 9/26/17.
 */
@Data
@AllArgsConstructor
public class CatergoryListDTO {

    List<CategoryDTO> categories;

}