package com.example.arezookaramooz.nilin.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Company {

    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("catchPhrase")
    @Expose
    String catchPhrase;
    @SerializedName("bs")
    @Expose
    String bs;

    public Company(String name, String catchPhrase, String bs) {

        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}
