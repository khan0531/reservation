package com.example.reservation.persist;

import com.example.reservation.model.Review;
import com.example.reservation.persist.entity.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByMemberId(Long memberId);

    List<ReviewEntity> findByRestaurantId(Long restaurantId);

    List<ReviewEntity> findByReservationId(Long reservationId);

    List<ReviewEntity> findByRestaurantIdAndMemberId(Long restaurantId, Long memberId);
}
