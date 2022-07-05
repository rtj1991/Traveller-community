package com.travellers.community.service.myTrips;

import com.travellers.community.dto.TripDto;
import com.travellers.community.model.Follower;
import com.travellers.community.model.MyTrip;
import com.travellers.community.model.Review;
import com.travellers.community.model.User;
import com.travellers.community.repository.FollowerRepository;
import com.travellers.community.repository.MyTripsRepository;
import com.travellers.community.repository.ReviewRepository;
import com.travellers.community.repository.UserRepository;
import com.travellers.community.util.Const;
import com.travellers.community.util.UtilManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyTripsServiceImpl implements MyTripsService{

    @Autowired
    private MyTripsRepository tripsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowerRepository followerRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public MyTrip createTrip(TripDto tripDto) {
        MyTrip myTrip = new MyTrip();
        myTrip.setLocation(tripDto.getLocation());
        myTrip.setDescription(tripDto.getDescription());
        myTrip.setStartDate(UtilManager.formatDate(tripDto.getStartDate()));
        myTrip.setEndDate(UtilManager.formatDate(tripDto.getEndDate()));
        if (tripDto.getStatus()==1){
            myTrip.setStatus(Const.VISISTED_PALCE);
        }else {
            myTrip.setStatus(Const.WANT_VISIST_PALCE);
        }
        return tripsRepository.save(myTrip);
    }

    @Override
    public List<MyTrip> getAllTrips() {
        User user = new User();
        user.setUser_id(1);
        return tripsRepository.findAllByUserIdNot(user);
    }

    @Override
    public Follower traverllerFollows(String follower, String followdby) {
        User followerId = userRepository.findById(Integer.valueOf(follower)).get();
        User followedbyId = userRepository.findById(Integer.valueOf(followdby)).get();
        Follower follower_ = new Follower();
        follower_.setFollower(followerId);
        follower_.setFollowedby(followedbyId);
        return followerRepository.save(follower_);
    }

    @Override
    public List<Review> getAllTripByReview(int id) {
        MyTrip myTrip = tripsRepository.findById(id).get();
        return reviewRepository.findAllByReviewer(myTrip);
    }
}
