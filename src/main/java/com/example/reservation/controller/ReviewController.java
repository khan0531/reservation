package com.example.reservation.controller;

import com.example.reservation.model.Review;
import com.example.reservation.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/review")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody Review review) {
        var result = this.reviewService.register(review);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> searchReview(@PathVariable Long reviewId) {
        var result = this.reviewService.getReview(reviewId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<?> searchReviewByMemberId(@PathVariable Long memberId) {
        var result = this.reviewService.getReviewByMemberId(memberId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<?> searchReviewByRestaurantId(@PathVariable Long restaurantId) {
        var result = this.reviewService.getReviewByRestaurantId(restaurantId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<?> searchReviewByReservationId(@PathVariable Long reservationId) {
        var result = this.reviewService.getReviewByReservationId(reservationId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/restaurant/{restaurantId}/member/{memberId}")
    public ResponseEntity<?> searchReviewByRestaurantIdAndMemberId(@PathVariable Long restaurantId, @PathVariable Long memberId) {
        var result = this.reviewService.getReviewByRestaurantIdAndMemberId(restaurantId, memberId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        var result = this.reviewService.deleteReview(reviewId);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<?> updateReview(@RequestBody Review review) {
        var result = this.reviewService.updateReview(review);
        return ResponseEntity.ok(result);
    }
}
