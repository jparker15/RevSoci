package com.atsignJar.Revenge.Society.controllers;

import com.atsignJar.Revenge.Society.models.geekout.Geekout;
import com.atsignJar.Revenge.Society.repositories.GeekoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/geekouts")
public class GeekoutController {
    @Autowired
    GeekoutRepository repository;

    @GetMapping
    public ResponseEntity<Iterable<Geekout>> getAll(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK) ;
    }

    @PostMapping
    public ResponseEntity<Geekout> createOne(@RequestBody Geekout geekout){
        System.out.println(geekout.getDeveloper().getId());
        return new ResponseEntity<>(repository.save(geekout),HttpStatus.CREATED);
    }


}
