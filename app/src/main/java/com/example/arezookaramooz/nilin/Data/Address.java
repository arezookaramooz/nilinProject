package com.example.arezookaramooz.nilin.Data;

public class Address {

    String street, suite, city, zipcode;
    Geo geo;

    public Address(String street, String suite, String city, String zipcode, Geo geo){

        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;

    }

}
