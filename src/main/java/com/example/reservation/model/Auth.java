package com.example.reservation.model;

import com.example.reservation.model.constants.Authority;
import com.example.reservation.persist.entity.MemberEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

public class Auth {

    @Data
    public static class SignIn {
        private String memberId;
        private String password;
    }

    @Data
    public static class SignUp {
        private String memberId;
        private String password;
        private String name;
        private String email;
        private List<Authority> roles;

        public MemberEntity toEntity() {
            return MemberEntity.builder()
                    .memberId(this.memberId)
                    .password(this.password)
                    .name(this.name)
                    .email(this.email)
                    .roles(this.roles)
                    .build();
        }
    }

}
