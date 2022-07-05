package com.travellers.community.service.myTrips;

import com.travellers.community.dto.TripDto;
import com.travellers.community.model.Follower;
import com.travellers.community.model.MyTrip;
import com.travellers.community.model.Review;

import java.util.List;

public interface MyTripsService {
    MyTrip createTrip(TripDto tripDto);

    List<MyTrip> getAllTrips();

    Follower traverllerFollows(String follower, String followdby);


    List<Review> getAllTripByReview(int id);
}
