package com.travellers.community.dataFetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.travellers.community.dto.ReviewDto;
import com.travellers.community.dto.TripDto;
import com.travellers.community.model.Follower;
import com.travellers.community.model.MyTrip;
import com.travellers.community.model.Review;
import com.travellers.community.service.myTrips.MyTripsService;
import com.travellers.community.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class TripDataFetcher {

    @Autowired
    private MyTripsService tripsService;

    @Autowired
    private ReviewService reviewService;

    @DgsMutation
    public MyTrip createTrip(TripDto tripInfo) {
        return tripsService.createTrip(tripInfo);
    }

    @DgsQuery
    public List<MyTrip> getAllTrip() {
        return tripsService.getAllTrips();
    }

    @DgsQuery
    public Follower followTraveller(@InputArgument("follower") String follower, @InputArgument("followdby") String followdby) {
        return tripsService.traverllerFollows(follower, followdby);
    }

    @DgsQuery
    public List<Review> getTripandReviewById(@InputArgument("id") int id) {
        return tripsService.getAllTripByReview(id);
    }

    @DgsMutation
    public Review createReview(@InputArgument("id")int id, ReviewDto review){
        return reviewService.createReview(id,review);
    }

}
