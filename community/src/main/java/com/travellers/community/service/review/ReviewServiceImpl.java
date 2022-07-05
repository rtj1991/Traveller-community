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

    @Autowired
    private MyTripsRepository tripsRepository;

    @Override
    public Review createReview(int id, ReviewDto review) {
        MyTrip myTrip = tripsRepository.findById(id).get();
        Review review_ = new Review();
        review_.setReviewer(myTrip);
        review_.setComment(review.getComment());
        review_.setRating(review.getRating());
        review_.setTimestampCreated(new Date());
        return reviewRepository.save(review_);
    }
}
