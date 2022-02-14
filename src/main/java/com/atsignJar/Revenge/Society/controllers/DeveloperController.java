package com.atsignJar.Revenge.Society.controllers;

import com.atsignJar.Revenge.Society.models.avatar.Avatar;
import com.atsignJar.Revenge.Society.models.developer.Developer;
import com.atsignJar.Revenge.Society.models.language.Language;
import com.atsignJar.Revenge.Society.repositories.AvatarRepository;
import com.atsignJar.Revenge.Society.repositories.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/developers")
public class DeveloperController {
    @Autowired
    private DeveloperRepository repository;

    @Autowired
    private AvatarRepository avatarRepository;

    //Get All
    @GetMapping
    public @ResponseBody List<Developer> getDevelopers(){return repository.findAll();}

    //Get by ID
    @GetMapping("/{id}")
    public @ResponseBody Developer getOneDeveloper(@PathVariable Long id){
        return repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //Get by Cohort
    @GetMapping("/cohort/{cohort}")
    public ResponseEntity<List<Developer>> getDeveloperByCohort(@PathVariable Integer cohort){
                //sorted by name
        return new ResponseEntity<>(repository.findAllByCohort(cohort, Sort.by("name")),HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer newDeveloper){
        return new ResponseEntity<>(repository.save(newDeveloper),HttpStatus.CREATED);
    }

    @PostMapping("/photo")
    public Developer addPhoto(@RequestBody Developer dev){

        Developer developer = repository.findById(dev.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(developer.getAvatar() != null){
            Avatar avatar = developer.getAvatar();
            avatar.setUrl(dev.getAvatar().getUrl());
            avatarRepository.save(avatar);
            return developer;
        }
        Avatar avatar = avatarRepository.save(dev.getAvatar());
        developer.setAvatar(avatar);

        return repository.save(developer);
    }

    @PutMapping("/languages/")
    public Developer addLanguage(@RequestBody Developer updates){
        Developer developer = repository.findById(updates.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        developer.languages.addAll(updates.languages);

        return repository.save(developer);
    }

    @PutMapping("/{id}")
    public @ResponseBody Developer updateDeveloper(@PathVariable Long id, @RequestBody Developer updates) {
        Developer developer = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getName() != null) developer.setName(updates.getName());
        if (updates.getEmail() != null) developer.setEmail(updates.getEmail());
        if (updates.getCohort() != null) developer.setCohort(updates.getCohort());
        if (updates.languages != null) developer.languages = updates.languages;

        return repository.save(developer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyDeveloper(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Developer Deleted", HttpStatus.OK);
    }


}
