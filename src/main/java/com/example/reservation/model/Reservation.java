package com.example.reservation.model;

import com.example.reservation.persist.entity.ReservationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    private Long id;

    private Long restaurantId;

    private Long memberId;

    private String reservationTime;

    public static Reservation fromEntity(ReservationEntity reservationEntity) {
        return Reservation.builder()
                .id(reservationEntity.getId())
                .restaurantId(reservationEntity.getRestaurantId())
                .memberId(reservationEntity.getMemberId())
                .reservationTime(reservationEntity.getReservationTime().toString())
                .build();
    }

    public ReservationEntity toEntity() {
        return ReservationEntity.builder()
                .id(this.id)
                .restaurantId(this.restaurantId)
                .memberId(this.memberId)
                .reservationTime(java.time.LocalDateTime.parse(this.reservationTime))
                .build();
    }
}
