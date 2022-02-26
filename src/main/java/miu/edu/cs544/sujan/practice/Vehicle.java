package miu.edu.cs544.sujan.practice;

import javax.persistence.*;

@Entity
@NamedNativeQuery(name = "findAllCars", query = "select * from vehicle as v where v.dtype='car'", resultClass = Vehicle.class)
public class Vehicle {
    @Id
    @GeneratedValue
    private Long id;

    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToOne(cascade=CascadeType.PERSIST)
    private Factory factory;

    public Vehicle() {
    }


    public Vehicle(int year) {
        this.year = year;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public int getYear() {
        return year;
    }
}