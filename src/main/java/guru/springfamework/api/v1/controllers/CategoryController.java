package guru.springfamework.api.v1.controllers;

import guru.springfamework.api.v1.domain.CatergoryListDTO;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/categories/")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CatergoryListDTO> getCatergores() {
        CatergoryListDTO catergoryListDTO = new CatergoryListDTO(categoryService.getAllCategories());
        return new ResponseEntity<CatergoryListDTO>(catergoryListDTO, HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {
        CategoryDTO categoryDTO = categoryService.getCategoryByName(name);
        return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
    }
}
