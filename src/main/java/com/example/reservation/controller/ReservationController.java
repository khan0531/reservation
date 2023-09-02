package com.example.reservation.controller;

import com.example.reservation.model.Reservation;
import com.example.reservation.service.ReservationService;
import com.example.reservation.service.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation) {
        var result = this.reservationService.register(reservation);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<?> searchReservationByMemberId(@PathVariable Long memberId) {
        var result = this.reservationService.getReservationByMemberId(memberId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<?> searchReservationByRestaurantId(@PathVariable Long restaurantId) {
        var result = this.reservationService.getReservationByRestaurantId(restaurantId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long reservationId) {
        var result = this.reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok(result);
    }
}
