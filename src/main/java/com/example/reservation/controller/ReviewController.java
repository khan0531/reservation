package com.example.reservation.controller;

import com.example.reservation.model.Review;
import com.example.reservation.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/review")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody Review review) {
        Review result = this.reviewService.register(review);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> searchReview(@PathVariable Long reviewId) {
        Review result = this.reviewService.getReview(reviewId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<?> searchReviewByReservationId(@PathVariable Long reservationId) {
        List<Review> result = this.reviewService.getReviewByReservationId(reservationId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        Long result = this.reviewService.deleteReview(reviewId);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<?> updateReview(@RequestBody Review review) {
        Review result = this.reviewService.updateReview(review);
        return ResponseEntity.ok(result);
    }
}
