package com.example.reservation.model;

import com.example.reservation.persist.entity.MemberEntity;
import com.example.reservation.persist.entity.ReservationEntity;
import com.example.reservation.persist.entity.RestaurantEntity;
import com.example.reservation.persist.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private Long id;

    private String title;

    private String content;

    private Long reservationId;

    private Long memberId;

    private Long restaurantId;

    public ReviewEntity toEntity() {
        return ReviewEntity.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .reservation(ReservationEntity.builder().id(this.reservationId).build())
                .member(MemberEntity.builder().id(this.memberId).build())
                .restaurant(RestaurantEntity.builder().id(this.restaurantId).build())
                .build();
    }

    public static Review fromEntity(ReviewEntity reviewEntity) {
        return Review.builder()
                .id(reviewEntity.getId())
                .title(reviewEntity.getTitle())
                .content(reviewEntity.getContent())
                .reservationId(reviewEntity.getReservation().getId())
                .memberId(reviewEntity.getMember().getId())
                .restaurantId(reviewEntity.getRestaurant().getId())
                .build();
    }
}
