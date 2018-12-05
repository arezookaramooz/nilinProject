package com.example.arezookaramooz.nilin.Data;

public class Album {

    String name, photoLink;

    public Album(String name, String photo) {
        this.name = name;
        this.photoLink = photo;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

}
