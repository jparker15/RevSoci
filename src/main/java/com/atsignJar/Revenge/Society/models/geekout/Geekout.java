package com.atsignJar.Revenge.Society.models.geekout;

import com.atsignJar.Revenge.Society.models.developer.Developer;

import javax.persistence.*;

@Entity
public class Geekout {

    @Id
    @GeneratedValue
    private Long id;

   @ManyToOne //connects all the posts to that developer's id
   @JoinColumn(name = "developer_id", referencedColumnName = "id")
   private Developer developer;


    private String title;
    private String content;

    public Geekout(){

    }

    public Geekout(Developer developer,String title,String content){
        this.developer = developer;
        this.title = title;
        this.content = content;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

