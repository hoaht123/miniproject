package com.example.miniproject.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AttempBook {

    private int id;

    private String title;

    private Double price;

    private String description;

    private String image;

    private Double avgRating;
}
