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
import org.springframework.security.access.prepost.PreAuthorize;

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
    @PreAuthorize("hasAnyRole('USER','ADMIN','PREMIUM')")
    @DgsQuery
    public List<MyTrip> getAllTrip() {
        return tripsService.getAllTrips();
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN','PREMIUM')")
    @DgsQuery
    public Follower followTraveller(@InputArgument("follower") String follower, @InputArgument("followdby") String followdby) {
        return tripsService.traverllerFollows(follower, followdby);
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN','PREMIUM')")
    @DgsQuery
    public Follower unFollowTraveller(@InputArgument("follower") String follower, @InputArgument("followdby") String followdby) {
        return tripsService.unFraverllerFollows(follower, followdby);
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN','PREMIUM')")
    @DgsQuery
    public List<Review> getTripandReviewById(@InputArgument("id") int id) {
        return tripsService.getAllTripByReview(id);
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN','PREMIUM')")
    @DgsMutation
    public Review createReview(@InputArgument("id") int id, ReviewDto review) {
        return reviewService.createReview(id, review);
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN','PREMIUM')")
    @DgsQuery
    public List<MyTrip> serarchTrips(@InputArgument("location") String location, @InputArgument("date") String date, @InputArgument("gender") int gender) {

        List<MyTrip>myTrips = null;
        if (!location.equals("") && !date.equals("") && gender != 0) {
            myTrips=tripsService.getTripByLocationDateGender(location,date,gender);
        }else if (!location.equals("") && !date.equals("") && gender==0){
            myTrips=tripsService.getTripByLocationDate(location,date);
        }else if (location.equals("") && !date.equals("") && gender!=0){
            myTrips=tripsService.getTripByDateGender(date,gender);
        }else if (!location.equals("") && date.equals("") && gender!=0){
            myTrips=tripsService.getTripByLocationGender(location,gender);
        }else if (!location.equals("") && date.equals("") && gender==0){
            myTrips=tripsService.getTripByLocation(location);
        }else if (location.equals("") && !date.equals("") && gender==0){
            myTrips=tripsService.getTripByDate(date);
        }else if (location.equals("") && date.equals("") && gender!=0){
            myTrips=tripsService.getTripByGender(gender);
        }
        return myTrips;
    }

}
