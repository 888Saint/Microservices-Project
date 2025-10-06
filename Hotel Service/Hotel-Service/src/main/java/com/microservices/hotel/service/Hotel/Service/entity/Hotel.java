package com.microservices.hotel.service.Hotel.Service.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "micro_hotels")
public class Hotel {

    @Id
    @Column
    String id;
    @Column(length = 50)
    String name;
    @Column
    String location;
    @Column
    String about;
}
