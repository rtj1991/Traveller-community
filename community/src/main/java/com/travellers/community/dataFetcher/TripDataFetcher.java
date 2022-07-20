package com.travellers.community.dataFetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.travellers.community.dto.FollowerDto;
import com.travellers.community.dto.ReviewDto;
import com.travellers.community.dto.TripDto;
import com.travellers.community.exceptions.TripNotFoundException;
import com.travellers.community.mapper.FollowerMapper;
import com.travellers.community.mapper.MyTripMapper;
import com.travellers.community.mapper.ReviewMapper;
import com.travellers.community.model.Follower;
import com.travellers.community.model.MyTrip;
import com.travellers.community.model.Review;
import com.travellers.community.model.User;
import com.travellers.community.repository.FollowerRepository;
import com.travellers.community.repository.MyTripsRepository;
import com.travellers.community.repository.UserRepository;
import com.travellers.community.service.myTrips.MyTripsService;
import com.travellers.community.service.review.ReviewService;
import com.travellers.community.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@DgsComponent
public class TripDataFetcher {

    @Autowired
    private MyTripsService tripsService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MyTripMapper myTripMapper;

    @Autowired
    private FollowerMapper followerMapper;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyTripsRepository tripsRepository;

    @Autowired
    private FollowerRepository followerRepository;

    @PreAuthorize("hasAnyRole('USERS','ADMIN','PREMIUM')")
    @DgsMutation(field = "createTrip")
    public MyTrip createTrip(@InputArgument TripDto tripInfo) {
        try {
            MyTrip myTrip = myTripMapper.modelToDto(tripInfo);
            if (myTrip==null)throw new TripNotFoundException();
            User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            myTrip.setUserId(user);

            return tripsService.createTrip(myTrip);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','PREMIUM')")
    @DgsQuery(field = "getAllTrip")
    public List<MyTrip> getAllTrip() {

        return tripsService.getAllTrips();
    }

    @PreAuthorize("hasAnyRole('USERS','ADMIN','PREMIUM')")
    @DgsQuery(field = "followTraveller")
    public Follower followTraveller(@InputArgument FollowerDto followerInfo) {
        Follower follower = followerMapper.modelToDto(followerInfo);
        follower.setStatus(Const.FOLLOW);
        return tripsService.traverllerFollows(follower);
    }

    @PreAuthorize("hasAnyRole('USERS','ADMIN','PREMIUM')")
    @DgsQuery(field = "unFollowTraveller")
    public Follower unFollowTraveller(@InputArgument FollowerDto followerInfo) {
        Follower follower = followerMapper.modelToDto(followerInfo);
        Follower follower_ = followerRepository.findByFollowedbyAndFollower(followerInfo.getFollowedby(), followerInfo.getFollower());
        follower.setId(follower_.getId());
        follower.setStatus(Const.UNFOLLOW);
        return tripsService.unFraverllerFollows(follower);
    }

    @PreAuthorize("hasAnyRole('USERS','ADMIN','PREMIUM')")
    @DgsQuery(field = "getTripandReviewById")
    public List<Review> getTripandReviewById(@InputArgument("id") int id) {
        return tripsService.getAllTripByReview(id);
    }

    @PreAuthorize("hasAnyRole('USERS','ADMIN','PREMIUM')")
    @DgsMutation(field = "createReview")
    public Review createReview(@InputArgument("id") int id, @InputArgument ReviewDto review) {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        MyTrip myTrip = tripsRepository.findById(id).orElse(null);
        Review review_ = reviewMapper.modelTDto(review);
        review_.setReviewer(myTrip);
        review_.setUserId(user);
        return reviewService.createReview(review_);
    }

    @PreAuthorize("hasAnyRole('USERS','ADMIN','PREMIUM')")
    @DgsQuery(field = "serarchTrips")
    public List<MyTrip> serarchTrips(@InputArgument("location") String location, @InputArgument("date") String date, @InputArgument("gender") int gender) {

        List<MyTrip> myTrips = null;
        if (!location.equals("") && !date.equals("") && gender != 0) {
            myTrips = tripsService.getTripByLocationDateGender(location, date, gender);
        } else if (!location.equals("") && !date.equals("") && gender == 0) {
            myTrips = tripsService.getTripByLocationDate(location, date);
        } else if (location.equals("") && !date.equals("") && gender != 0) {
            myTrips = tripsService.getTripByDateGender(date, gender);
        } else if (!location.equals("") && date.equals("") && gender != 0) {
            myTrips = tripsService.getTripByLocationGender(location, gender);
        } else if (!location.equals("") && date.equals("") && gender == 0) {
            myTrips = tripsService.getTripByLocation(location);
        } else if (location.equals("") && !date.equals("") && gender == 0) {
            myTrips = tripsService.getTripByDate(date);
        } else if (location.equals("") && date.equals("") && gender != 0) {
            myTrips = tripsService.getTripByGender(gender);
        }
        return myTrips;
    }

}
