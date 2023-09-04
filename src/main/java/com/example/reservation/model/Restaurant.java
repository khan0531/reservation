package com.example.reservation.model;

import com.example.reservation.persist.entity.MemberEntity;
import com.example.reservation.persist.entity.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    private Long id;

    private String name;

    private String address;

    private String explanation;

    private Long memberId;

    public RestaurantEntity toEntity() {
        return RestaurantEntity.builder()
                .id(this.id)
                .name(this.name)
                .address(this.address)
                .explanation(this.explanation)
                .member(MemberEntity.builder().id(this.memberId).build())
                .build();
    }

    public static Restaurant fromEntity(RestaurantEntity restaurantEntity) {
        return Restaurant.builder()
                .id(restaurantEntity.getId())
                .name(restaurantEntity.getName())
                .address(restaurantEntity.getAddress())
                .explanation(restaurantEntity.getExplanation())
                .memberId(restaurantEntity.getMember().getId())
                .build();
    }
}
