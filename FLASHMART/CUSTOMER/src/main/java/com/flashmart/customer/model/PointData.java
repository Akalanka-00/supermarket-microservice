package com.flashmart.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pointCode;

    private int discount;

    private int limitValue;

    private String description;

}
