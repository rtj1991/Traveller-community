package com.travellers.community.service.review;

import com.travellers.community.dto.ReviewDto;
import com.travellers.community.model.Review;

public interface ReviewService {
    Review createReview(Review review);
}
