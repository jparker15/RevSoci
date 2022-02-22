package com.atsignJar.Revenge.Society.models.approve;

import com.atsignJar.Revenge.Society.models.developer.Developer;
import com.atsignJar.Revenge.Society.models.geekout.Geekout;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.persistence.*;

@Entity
public class Approve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id", referencedColumnName = "id")
    @JsonIncludeProperties("id")
    private Developer developer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "geekout_id", referencedColumnName = "id")
    @JsonIncludeProperties("id")
    private Geekout geekout;

    public Approve() {

    }

    public Approve(Developer developer, Geekout geekout) {
        this.developer = developer;
        this.geekout = geekout;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public Geekout getGeekout() {
        return geekout;
    }

    public void setGeekout(Geekout geekout) {
        this.geekout = geekout;
    }
}
