package com.mentor4you.service;

import com.mentor4you.exception.MentorNotFoundException;
import com.mentor4you.model.*;
import com.mentor4you.repository.AccountRepository;
import com.mentor4you.repository.CategoriesRepository;
import com.mentor4you.repository.MenteeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {



    @Autowired
    CategoriesRepository categoriesRepository;


    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }


    public List<Categories> getFullInfoAllCategories(){

            return categoriesRepository.findAll();
    }

    public Optional<Categories> getCategoriesById(int id){
        Optional<Categories> theCategory = categoriesRepository.findById(id).stream().filter(e->e.getId()==id).findFirst();
        if(theCategory.isPresent()) {
            return theCategory;
        }
        throw new MentorNotFoundException("Category with id = "+ id +" not found");
    }
}
