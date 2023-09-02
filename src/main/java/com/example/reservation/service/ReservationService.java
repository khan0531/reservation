package com.example.reservation.service;

import com.example.reservation.model.Reservation;
import com.example.reservation.persist.ReservationRepository;
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

    public Reservation register(Reservation reservation) {
        boolean exist = this.reservationRepository.existsByMemberId(reservation.getMemberId());
        if (exist) {
            throw new IllegalArgumentException("이미 예약한 회원입니다.");
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

    public Reservation checkReservation(Reservation reservation) {
        boolean exist = this.reservationRepository.existsById(reservation.getId());
        if (!exist) {
            throw new IllegalArgumentException("없는 예약입니다.");
        } else if (timeCheck(reservation.getReservationTime())) {
            throw new IllegalArgumentException("이미 지난 시간입니다.");
        }

        var result = this.reservationRepository.save(reservation.toEntity());
        return Reservation.fromEntity(result);
    }

    public boolean timeCheck(String reservationTime) {
        LocalDateTime now = LocalDateTime.now();
        // 10분 넘어가면 false 반환
        return now.isAfter(LocalDateTime.parse(reservationTime).plusMinutes(10));
    }


}
