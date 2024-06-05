package com.example.carhire.controller;

import com.example.carhire.entity.Category;
import com.example.carhire.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<?> findAllCategories(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/categories")
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(category));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable Long id){
        Category existingCategory= categoryService.findCategoryById(id);

        if(existingCategory!=null){
            return ResponseEntity.status(HttpStatus.FOUND).body(existingCategory);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Category Found For Given Id");
        }
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id,@RequestBody Category category){

        Category updatedCategory=categoryService.updateCategory(id,category);

        if(updatedCategory!=null){
            try{
                return ResponseEntity.status(HttpStatus.OK).body(updatedCategory);
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Category Found For Given Id");
        }

    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        Boolean hasDeleted=categoryService.deleteCategory(id);

        if(hasDeleted){
            return  ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted The Category");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category Not Found For Given Id");
        }
    }
}
