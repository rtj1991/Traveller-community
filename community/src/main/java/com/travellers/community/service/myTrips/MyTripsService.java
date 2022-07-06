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
    Follower unFraverllerFollows(String follower, String followdby);


    List<Review> getAllTripByReview(int id);

    List<MyTrip> getTripByLocationDateGender(String location, String date, int gender);

    List<MyTrip> getTripByLocationDate(String location, String date);

    List<MyTrip> getTripByDateGender(String date, int gender);

    List<MyTrip> getTripByLocationGender(String location, int gender);

    List<MyTrip> getTripByLocation(String location);

    List<MyTrip> getTripByDate(String date);

    List<MyTrip> getTripByGender(int gender);
}
