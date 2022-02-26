package miu.edu.cs544.sujan.practice;

import javax.persistence.*;

@Entity
public class Car extends Vehicle{
    @Id
    @GeneratedValue
    private Long id;

    private int millage;

    public Car() {
    }

    public Car(int year, int millage) {
        super(year);
        this.millage = millage;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", millage=" + millage +
                '}';
    }
}