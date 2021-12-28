package com.atsignJar.Revenge.Society.controllers;

import com.atsignJar.Revenge.Society.models.geekout.Geekout;
import com.atsignJar.Revenge.Society.repositories.GeekoutRepository;
import org.hibernate.hql.internal.ast.tree.ResolvableNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @PutMapping("/{id}")
    public @ResponseBody Geekout updateOne(@PathVariable Long id, @RequestBody Geekout updates){

        Geekout geekout = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(updates.getContent() != null) geekout.setContent(updates.getContent());
        if(updates.getTitle() != null) geekout.setTitle(updates.getTitle());
        if(updates.getDeveloper() != null) geekout.setDeveloper(updates.getDeveloper());
        return repository.save(geekout);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOneById(@PathVariable long id){
        repository.deleteById(id);
        return new ResponseEntity<>("Geekout Deleted",HttpStatus.OK);
    }

    @GetMapping("/dev/{devId}")
    public ResponseEntity<List<Geekout>> getByDevId(@PathVariable Long devId){
        return new ResponseEntity<>(repository.findByDeveloperId(devId),HttpStatus.OK);
    }

}
