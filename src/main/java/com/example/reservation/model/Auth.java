package com.example.reservation.model;

import com.example.reservation.persist.entity.MemberEntity;
import lombok.Data;

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
        private List<String> roles;

        public MemberEntity toEntity() {
            return MemberEntity.builder()
                    .memberId(this.memberId)
                    .password(this.password)
                    .roles(this.roles)
                    .build();
        }
    }

}
