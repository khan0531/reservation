package com.example.reservation.persist;

import com.example.reservation.persist.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    boolean existsByMemberId(String memberId);

    Optional<MemberEntity> findByMemberId(String memberId);
}
