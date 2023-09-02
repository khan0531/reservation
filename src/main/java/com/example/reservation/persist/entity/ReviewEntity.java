package com.example.reservation.persist.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Entity(name = "REVIEW")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReviewEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;

        private String content;

        private Long reservationId;

        private Long memberId;

        private Long restaurantId;
}
