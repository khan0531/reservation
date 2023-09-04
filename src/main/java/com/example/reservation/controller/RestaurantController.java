package com.example.reservation.controller;

import com.example.reservation.model.Reservation;
import com.example.reservation.model.Restaurant;
import com.example.reservation.model.Review;
import com.example.reservation.service.ReservationService;
import com.example.reservation.service.RestaurantService;
import com.example.reservation.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurant")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final ReviewService reviewService;
    private final ReservationService reservationService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> addRestaurant(@RequestBody Restaurant restaurantRequest) {
        Restaurant restaurant = this.restaurantService.register(restaurantRequest);
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping
    public ResponseEntity<?> searchAllRestaurant(final Pageable pageable) {
        Page<Restaurant> result = this.restaurantService.getAllRestaurant(pageable);
        return ResponseEntity.ok(result);
    }

    // TODO : Not Found Exception 처리
    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurant(@PathVariable Long id) {
        Restaurant result = this.restaurantService.getRestaurant(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/autocomplete")
    public ResponseEntity<?> autocomplete(@RequestParam String keyword) {
        List<Restaurant> result = this.restaurantService.getRestaurantNameByKeyword(keyword);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> updateRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant result = this.restaurantService.updateRestaurant(restaurant);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long id) {
        Long result = this.restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("{id}/reviews")
    public ResponseEntity<?> getReviews(@PathVariable Long id) {
        List<Review> result = this.reviewService.getReviewByRestaurantId(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("{id}/reservations")
    public ResponseEntity<?> getReservations(@PathVariable Long id) {
        List<Reservation> result = this.reservationService.getReservationByRestaurantId(id);
        return ResponseEntity.ok(result);
    }
}
