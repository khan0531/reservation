package com.example.reservation.controller;

import com.example.reservation.model.Reservation;
import com.example.reservation.model.Review;
import com.example.reservation.service.ReservationService;
import com.example.reservation.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/members")
@AllArgsConstructor
public class MemberController {

    private final ReservationService reservationService;
    private final ReviewService reviewService;

    @GetMapping("/{id}/reservations")
    public ResponseEntity<?> getReservationByMemberId(@PathVariable Long id) {
        List<Reservation> result = this.reservationService.getReservationByMemberId(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<?> getReviewByMemberId(@PathVariable Long id) {
        List<Review> result = this.reviewService.getReviewByMemberId(id);
        return ResponseEntity.ok(result);
    }
}
