package com.example.reservation.controller;

import com.example.reservation.dto.ReservationRequestDto;
import com.example.reservation.model.Reservation;
import com.example.reservation.service.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/reservations")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> addReservation(@RequestBody ReservationRequestDto dto) {
        Reservation result = this.reservationService.register(dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long reservationId) {
        Long result = this.reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{reservationId}/check")
    public ResponseEntity<?> checkReservation(@PathVariable Long reservationId) {
        Reservation result = this.reservationService.checkReservation(reservationId);
        return ResponseEntity.ok(result);
    }
}
