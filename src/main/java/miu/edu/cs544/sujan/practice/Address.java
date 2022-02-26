package miu.edu.cs544.sujan.practice;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String state;
    private String city;

    public Address() {
    }

    public Address(String street, String state, String city) {
        this.street = street;
        this.state = state;
        this.city = city;
    }
}