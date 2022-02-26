package miu.edu.cs544.sujan.practice;

import javax.persistence.*;

@Entity
public class Van extends Vehicle {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private VehicleColor color;

    public Van() {
    }

    public Van(VehicleColor color) {
        this.color = color;
    }
}