package com.mentor4you.controller;

import com.mentor4you.model.Mongo.Mongo;
import com.mentor4you.repository.MongoDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/mongo")
public class MongodbController {

    @Autowired
    private MongoDBRepository mongoRepository;

    @PostMapping
    void add(){
        List<Mongo> list  = new ArrayList<>();
        list.add(new Mongo(1,"Alex","Lutsk",21));
        list.add(new Mongo(2,"Andrey","Rivne",22));
        list.add(new Mongo(3,"Oleh","Kyiv",25));
        list.add(new Mongo(4,"Inna","Odessa",20));
        list.add(new Mongo(5,"Julia","Poltava",21));
        mongoRepository.saveAll(list);
    }

    @DeleteMapping
    void delete(){
        mongoRepository.deleteAll();
    }

    @GetMapping
    List<Mongo> getAll(){
        return mongoRepository.findAll();
    }
}
