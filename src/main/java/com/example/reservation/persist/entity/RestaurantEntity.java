package com.example.reservation.persist.entity;

import javax.persistence.*;

import lombok.*;

@Builder
@Entity(name = "RESTAURANT")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String explanation;

    @ManyToOne
    private MemberEntity member;
}
