package com.example.reservation.controller;

import com.example.reservation.model.Restaurant;
import com.example.reservation.service.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/restaurant")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    @PostMapping
    public ResponseEntity<?> addRestaurant(@RequestBody Restaurant restaurant) {
        var result = this.restaurantService.register(restaurant);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> searchAllRestaurant(final Pageable pageable) {
        Page<Restaurant> result = this.restaurantService.getAllRestaurant(pageable);
        return ResponseEntity.ok(result);
    }

//    @GetMapping
//    public ResponseEntity<?> getRestaurant(@PathVariable Long id) {
//        var result = this.restaurantService.getRestaurant(id);
//        return ResponseEntity.ok(result);
//    }

    @GetMapping("/autocomplete")
    public ResponseEntity<?> autocomplete(@RequestParam String keyword) {
        var result = this.restaurantService.getRestaurantNameByKeyword(keyword);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<?> updateRestaurant(@RequestBody Restaurant restaurant) {
        var result = this.restaurantService.updateRestaurant(restaurant);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long id) {
        Long result = this.restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok(result);
    }
}
