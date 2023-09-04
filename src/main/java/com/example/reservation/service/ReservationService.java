package com.example.reservation.service;

import com.example.reservation.dto.ReservationRequestDto;
import com.example.reservation.model.Reservation;
import com.example.reservation.persist.ReservationRepository;
import com.example.reservation.persist.entity.ReservationEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public Reservation register(ReservationRequestDto dto) {
        Reservation reservation = Reservation.fromDto(dto);

        boolean exist = this.reservationRepository.existsByMemberId(reservation.getMemberId());
        if (exist) {
            throw new IllegalArgumentException("현재 예약이 존재합니다.");
        }
        var result = this.reservationRepository.save(reservation.toEntity());
        return Reservation.fromEntity(result);
    }

    public List<Reservation> getReservationByRestaurantId(Long restaurantId) {
        List<Reservation> reservations = this.reservationRepository.findByRestaurantId(restaurantId);
        return reservations;
    }

    public List<Reservation> getReservationByMemberId(Long memberId) {
        List<Reservation> reservations = this.reservationRepository.findByMemberId(memberId);
        return reservations;
    }

    public Long deleteReservation(Long id) {
        var reservation = this.reservationRepository.findById(id).orElseThrow();

        this.reservationRepository.delete(reservation);
        return reservation.getId();
    }
    /*
    도착 10분 체기
     */
    public Reservation checkReservation(Long id) {
        ReservationEntity reservationEntity = this.reservationRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없는 예약입니다.")
        );
        Reservation reservation = Reservation.fromEntity(reservationEntity);
        reservation.checkTime();

        var result = this.reservationRepository.save(reservation.toEntity());
        return Reservation.fromEntity(result);
    }
}
