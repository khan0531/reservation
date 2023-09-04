package com.example.reservation.persist.entity;

import lombok.*;

import javax.persistence.*;

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

        @OneToOne
        private ReservationEntity reservation;

        @ManyToOne
        private MemberEntity member;

        @ManyToOne
        private RestaurantEntity restaurant;

}
