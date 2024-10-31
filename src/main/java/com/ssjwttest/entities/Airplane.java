package com.ssjwttest.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Airplane {
    public enum TYPE{
        SHORT,
        MEDIUM,
        LONG
    } ;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AirplaneId ;
    private String Name ;
    private TYPE AirplaneType ;
    private double Consumption;
    private double Capacity ;
    private double Autonomy ;
}
