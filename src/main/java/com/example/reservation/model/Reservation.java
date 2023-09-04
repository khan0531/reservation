package com.example.reservation.model;

import com.example.reservation.dto.ReservationRequestDto;
import com.example.reservation.model.constants.ReservationStatus;
import com.example.reservation.persist.entity.MemberEntity;
import com.example.reservation.persist.entity.ReservationEntity;
import com.example.reservation.persist.entity.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    private Long id;

    private Long restaurantId;

    private Long memberId;

    private String reservationTime;

    private ReservationStatus status;

    public static Reservation fromDto(ReservationRequestDto reservationRequestDto) {
        return Reservation.builder()
                .restaurantId(reservationRequestDto.getRestaurantId())
                .memberId(reservationRequestDto.getMemberId())
                .reservationTime(reservationRequestDto.getReservationTime())
                .status(ReservationStatus.WAITING)
                .build();
    }

    public static Reservation fromEntity(ReservationEntity reservationEntity) {
        return Reservation.builder()
                .id(reservationEntity.getId())
                .restaurantId(reservationEntity.getRestaurant().getId())
                .memberId(reservationEntity.getMember().getId())
                .reservationTime(reservationEntity.getReservationTime().toString())
                .status(reservationEntity.getStatus())
                .build();
    }

    public ReservationEntity toEntity() {
        return ReservationEntity.builder()
                .id(this.id)
                .restaurant(RestaurantEntity.builder().id(this.restaurantId).build())
                .member(MemberEntity.builder().id(this.memberId).build())
                .reservationTime(java.time.LocalDateTime.parse(this.reservationTime))
                .status(this.status)
                .build();
    }

    public void checkTime() {
        if (this.status != ReservationStatus.ACCEPTED) {
            throw new IllegalArgumentException("이미 도착하셨습니다.");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime reservationTime = LocalDateTime.parse(this.reservationTime);
        if (now.isAfter(reservationTime.plusMinutes(10))) {
            this.status = ReservationStatus.CANCELLED;
        } else {
            this.status = ReservationStatus.ARRIVED;
        }
    }
}
