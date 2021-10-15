package com.mentor4you.controller;

import com.mentor4you.model.Categories;
import com.mentor4you.model.Cooperation;
import com.mentor4you.model.Mentees;
import com.mentor4you.service.CategoriesService;
import com.mentor4you.service.MenteeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/cooperation")
public class CategoriesController {


    @Autowired
    private CategoriesService categoriesService;

    @GetMapping
    public List<Categories> getFullInfoAllCategories(){
        return categoriesService.getFullInfoAllCategories();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    ResponseEntity<?> addNewCategory(@RequestBody Categories categories){
        Map<String, String> res = new HashMap<>();
        if(!categories.getName().equals("") || categories.getName()==null){
            categoriesService.addNewCategory(categories.getName());
            res.put("message","New category was added");
            return ResponseEntity.status(201).body(res);
        }else {
            res.put("message","Field name cannot be null or empty");
            return ResponseEntity.badRequest().body(res);
        }
    }

}
