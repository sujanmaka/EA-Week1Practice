package miu.edu.cs544.sujan.practice;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "person")
@NamedQuery(name = "findVehiclesOwnByAPerson", query = "select p.vehicles from Person as p where p.name=:name")
public class Person {
    @Id
    @GeneratedValue
    @Column(name = "person_id")
    private Long id;

    private String name;
    private int age;

    @OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST, targetEntity = Vehicle.class)
    private List<Vehicle> vehicles;


    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", vehicles=" + vehicles +
                '}';
    }
}