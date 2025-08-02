package pl.pcdevs.systemfinansowy.model;

import jakarta.persistence.*;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String googleId; // = sub id from google

//    @Column
//    private String name;
//
//    @Column
//    private String picture;

    public AppUser() { }

    public AppUser(String googleId) {
        this.googleId = googleId;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPicture() {
//        return picture;
//    }
//
//    public void setPicture(String picture) {
//        this.picture = picture;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }
}
