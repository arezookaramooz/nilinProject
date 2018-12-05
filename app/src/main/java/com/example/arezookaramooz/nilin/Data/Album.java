package com.example.arezookaramooz.nilin.Data;

import java.util.ArrayList;

public class Album {

    String name;
    ArrayList<String> photoLinks = new ArrayList<String>();

    public Album(String name, ArrayList<String> photoLinks) {
        this.name = name;
        this.photoLinks = photoLinks;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public ArrayList<String> getPhotoLinks() {
        return photoLinks;
    }

    public void setPhotoLinks(ArrayList<String> photoLinks) {
        this.photoLinks = photoLinks;
    }

}
