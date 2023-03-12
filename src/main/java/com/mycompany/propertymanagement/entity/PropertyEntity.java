package com.mycompany.propertymanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PROPERTY_TABLE")
@Data
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private String OwnerName;
    private String OwnerEmail;
    private Double price;
    private String address;
}
