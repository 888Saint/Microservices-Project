package com.microservices.user.service.User.Service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "micro_users")
@Builder
public class User {
    @Id
    @Column(name = "ID")
    String userId;
    @Column(name = "NAME", length = 30)
    String name;
    @Column(name = "EMAIL")
    String email;
    @Column(name = "ABOUT")
    String about;
    @Transient
    List<Rating> ratings = new ArrayList<>();
}
