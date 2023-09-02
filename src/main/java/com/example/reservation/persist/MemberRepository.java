package com.example.reservation.persist;

import com.example.reservation.persist.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity getByMemberId(String memberId);
}
