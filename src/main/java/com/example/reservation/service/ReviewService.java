package com.example.reservation.service;

import com.example.reservation.exception.impl.NoReviewException;
import com.example.reservation.model.Review;
import com.example.reservation.persist.ReviewRepository;
import com.example.reservation.persist.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;

    public Review register(Review review) {
        boolean exist = this.reviewRepository.existsById(review.getId());
        if (exist) {
            throw new IllegalArgumentException("이미 존재하는 리뷰입니다.");
        }

        var result = this.reviewRepository.save(review.toEntity());

        return Review.fromEntity(result);
    }

    public Review getReview(Long id) {
        var review = this.reviewRepository.findById(id)
                .orElseThrow(() -> new NoReviewException());

        return Review.fromEntity(review);
    }

    public List<Review> getReviewByMemberId(Long memberId) {
        List<ReviewEntity> reviews = this.reviewRepository.findByMemberId(memberId);

        return reviews.stream()
                .map(Review::fromEntity)
                .collect(Collectors.toList());
    }

    public List<Review> getReviewByRestaurantId(Long restaurantId) {
        List<ReviewEntity> reviews = this.reviewRepository.findByRestaurantId(restaurantId);

        return reviews.stream()
                .map(Review::fromEntity)
                .collect(Collectors.toList());
    }

    public List<Review> getReviewByReservationId(Long reservationId) {
        List<ReviewEntity> reviews = this.reviewRepository.findByReservationId(reservationId);

        return reviews.stream()
                .map(Review::fromEntity)
                .collect(Collectors.toList());
    }

    public Long deleteReview(Long reviewId) {
        var review = this.reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoReviewException());

        this.reviewRepository.delete(review);
        return review.getId();
    }

    public Review updateReview(Review review) {
        boolean exist = this.reviewRepository.existsById(review.getId());

        if (!exist) {
            throw new NoReviewException();
        }

        var result = this.reviewRepository.save(review.toEntity());

        return Review.fromEntity(result);
    }
}
