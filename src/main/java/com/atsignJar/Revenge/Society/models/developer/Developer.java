package com.atsignJar.Revenge.Society.models.developer;

import com.atsignJar.Revenge.Society.models.geekout.Geekout;
import com.atsignJar.Revenge.Society.models.language.Language;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
public class Developer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private Integer cohort;
//    private String[] languages;
    
    @JsonBackReference
    @OneToMany
    @JoinColumn(name = "developer_id",referencedColumnName = "id")
    private List<Geekout> geekouts;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "developer_language",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    public Set <Language> languages;

    public Developer() {}

    public Developer(String name, String email, Integer cohort, List<Geekout> geekouts, Set<Language> languages) {
        this.name = name;
        this.email = email;
        this.cohort = cohort;
        this.geekouts = geekouts;
        this.languages = languages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCohort() {
        return cohort;
    }

    public void setCohort(Integer cohort) {
        this.cohort = cohort;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public List<Geekout> getGeekouts() {
        return geekouts;
    }

    public void setGeekouts(List<Geekout> geekouts) {
        this.geekouts = geekouts;
    }
}
