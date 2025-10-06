package com.microservices.user.service.User.Service.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Rating {
    Long ratingId;
    String userId;
    String hotelId;
    int rating;
    String feedback;
    Hotel hotel;
}
