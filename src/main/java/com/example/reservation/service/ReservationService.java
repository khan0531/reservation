package com.example.reservation.service;

import com.example.reservation.dto.ReservationRequestDto;
import com.example.reservation.exception.impl.NoReservationException;
import com.example.reservation.exception.impl.NoReviewException;
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
        var reservation = this.reservationRepository.findById(id)
                .orElseThrow(() -> new NoReservationException());

        this.reservationRepository.delete(reservation);
        return reservation.getId();
    }
    /*
    도착 10분 체기
     */
    public Reservation checkReservation(Long id) {
        ReservationEntity reservationEntity = this.reservationRepository.findById(id).orElseThrow(
                () -> new NoReservationException());
        Reservation reservation = Reservation.fromEntity(reservationEntity);
        reservation.checkTime();

        var result = this.reservationRepository.save(reservation.toEntity());
        return Reservation.fromEntity(result);
    }

    public Reservation acceptReservation(Long id) {
        ReservationEntity reservationEntity = this.reservationRepository.findById(id).orElseThrow(
                () -> new NoReviewException());
        Reservation reservation = Reservation.fromEntity(reservationEntity);
        reservation.acceptReservation();

        var result = this.reservationRepository.save(reservation.toEntity());
        return Reservation.fromEntity(result);
    }

    public Reservation rejectReservation(Long id) {
        ReservationEntity reservationEntity = this.reservationRepository.findById(id).orElseThrow(
                () -> new NoReservationException());
        Reservation reservation = Reservation.fromEntity(reservationEntity);
        reservation.rejectReservation();

        var result = this.reservationRepository.save(reservation.toEntity());
        return Reservation.fromEntity(result);
    }

    public Reservation cancelReservation(Long id) {
        ReservationEntity reservationEntity = this.reservationRepository.findById(id).orElseThrow(
                () -> new NoReservationException());
        Reservation reservation = Reservation.fromEntity(reservationEntity);
        reservation.cancelReservation();

        var result = this.reservationRepository.save(reservation.toEntity());
        return Reservation.fromEntity(result);
    }
}
