package com.example.reservation.persist;

import com.example.reservation.model.Reservation;
import com.example.reservation.persist.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    boolean existsByMemberId(Long memberId);

    List<Reservation> findByRestaurantId(Long restaurantId);

    List<Reservation> findByMemberId(Long memberId);

}
