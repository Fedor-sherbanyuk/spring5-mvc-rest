package guru.springfamework.api.v1.controllers;

import guru.springfamework.api.v1.domain.CatergoryListDTO;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CategoryController.BASE_URLCategory)
//@RequestMapping("${some.url.value}")
public class CategoryController {
    public static final String BASE_URL="/api/v1/customers/";
    public static final String BASE_URLCategory="/api/v1/categories/";
    public static final String BASE_URL_VENDOR="/api/v1/vendors/";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CatergoryListDTO getCatergores() {
        return new CatergoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }
}
