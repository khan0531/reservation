package com.example.reservation.service;

import com.example.reservation.model.Restaurant;
import com.example.reservation.persist.RestaurantRepository;
import com.example.reservation.persist.entity.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class RestaurantService {

    //private final Trie trie;

    private final RestaurantRepository restaurantRepository;

    public Restaurant register(Restaurant restaurant) {
        RestaurantEntity result = this.restaurantRepository.save(restaurant.toEntity());

        return Restaurant.fromEntity(result);
    }

    public List<Restaurant> getRestaurantNameByKeyword(String keyword) {
        List<RestaurantEntity> restaurantEntities = this.restaurantRepository.findByNameStartingWithIgnoreCase(keyword);

        return restaurantEntities.stream()
                .map(Restaurant::fromEntity)
                .collect(Collectors.toList());
    }

    public Page<Restaurant> getAllRestaurant (Pageable pageable) {
        Page<RestaurantEntity> result = this.restaurantRepository.findAll(pageable);

        return result.map(Restaurant::fromEntity);
    }

    public Long deleteRestaurant(Long id) {
        var restaurant = this.restaurantRepository.findById(id).orElseThrow();

        this.restaurantRepository.delete(restaurant);
        return id;
    }

    public Restaurant updateRestaurant(Restaurant restaurant) {
        boolean exist = this.restaurantRepository.existsById(restaurant.getId());
        if (!exist) {
            throw new IllegalArgumentException("존재하지 않는 음식점입니다.");
        }
        var result = this.restaurantRepository.save(restaurant.toEntity());

        return Restaurant.fromEntity(result);
    }
}
