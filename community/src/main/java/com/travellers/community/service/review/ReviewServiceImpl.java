package com.travellers.community.service.review;

import com.travellers.community.dto.ReviewDto;
import com.travellers.community.model.MyTrip;
import com.travellers.community.model.Review;
import com.travellers.community.repository.MyTripsRepository;
import com.travellers.community.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }
}
