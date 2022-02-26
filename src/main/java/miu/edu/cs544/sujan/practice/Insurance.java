package miu.edu.cs544.sujan.practice;

import javax.persistence.*;

@Entity
public class Insurance {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private Address address;

    public Insurance() {
    }

    public Insurance(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}