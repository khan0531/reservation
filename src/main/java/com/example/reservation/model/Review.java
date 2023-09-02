package com.example.reservation.model;

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
                .reservationId(this.reservationId)
                .memberId(this.memberId)
                .restaurantId(this.restaurantId)
                .build();
    }

    public static Review fromEntity(ReviewEntity reviewEntity) {
        return Review.builder()
                .id(reviewEntity.getId())
                .title(reviewEntity.getTitle())
                .content(reviewEntity.getContent())
                .reservationId(reviewEntity.getReservationId())
                .memberId(reviewEntity.getMemberId())
                .restaurantId(reviewEntity.getRestaurantId())
                .build();
    }
}
