package pl.pcdevs.systemfinansowy.dto;


public class UserProfileDto {
    private String sub;
    private String name;
    private String picture;

    public UserProfileDto(String sub, String name, String picture) {
        this.sub = sub;
        this.name = name;
        this.picture = picture;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}


