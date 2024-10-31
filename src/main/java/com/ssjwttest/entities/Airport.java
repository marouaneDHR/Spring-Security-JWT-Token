package com.ssjwttest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Airport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;
    @Embedded
    private Coordinate coordinate ;
}
