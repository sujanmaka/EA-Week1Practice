package miu.edu.cs544.sujan.practice;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "factory")
public class Factory {
    @Id
    @GeneratedValue
    private Long id;

    private int year;
    private int total_emp;
    @OneToMany
    private List<Vehicle> vehicles;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Insurance insurance;

    public Factory() {
    }

    public Factory(int year, int total_emp, List<Vehicle> vehicles) {
        this.year = year;
        this.total_emp = total_emp;
        this.vehicles = vehicles;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    @Override
    public String toString() {
        return "Factory{" +
                "id=" + id +
                ", year=" + year +
                ", total_emp=" + total_emp +
                ", vehicles=" + vehicles +
                ", insurance=" + insurance +
                '}';
    }
}