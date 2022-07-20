package com.travellers.community.service.myTrips;

import com.travellers.community.model.Follower;
import com.travellers.community.model.MyTrip;
import com.travellers.community.model.Review;
import com.travellers.community.model.User;
import com.travellers.community.repository.FollowerRepository;
import com.travellers.community.repository.MyTripsRepository;
import com.travellers.community.repository.ReviewRepository;
import com.travellers.community.util.UtilManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyTripsServiceImpl implements MyTripsService {

    @Autowired
    private MyTripsRepository tripsRepository;

    @Autowired
    private FollowerRepository followerRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public MyTrip createTrip(MyTrip myTrip) {
        return tripsRepository.save(myTrip);
    }

    @Override
    public List<MyTrip> getAllTrips() {
        User user = new User();
        user.setUser_id(1);
        return tripsRepository.findAllByUserIdNot(user);
    }

    @Override
    public Follower traverllerFollows(Follower follower) {
        return followerRepository.save(follower);
    }

    @Override
    public List<Review> getAllTripByReview(int id) {
        MyTrip myTrip = tripsRepository.findById(id).orElse(null);
        return reviewRepository.findAllByReviewer(myTrip);
    }

    @Override
    public Follower unFraverllerFollows(Follower follower) {
        return followerRepository.save(follower);
    }

    @Override
    public List<MyTrip> getTripByLocationDateGender(String location, String date, int gender) {
        return tripsRepository.findAllByLocationAndStartDateAndUserIdGender(location,UtilManager.formatDate(date),gender);
    }

    @Override
    public List<MyTrip> getTripByLocationDate(String location, String date) {
        return tripsRepository.findAllByLocationAndStartDate(location,UtilManager.formatDate(date));
    }

    @Override
    public List<MyTrip> getTripByDateGender(String date, int gender) {
        return tripsRepository.findAllByStartDateAndUserIdGender(UtilManager.formatDate(date),gender);
    }

    @Override
    public List<MyTrip> getTripByLocationGender(String location, int gender) {
        return tripsRepository.findAllByLocationAndUserIdGender(location,gender);
    }

    @Override
    public List<MyTrip> getTripByLocation(String location) {
        return tripsRepository.findAllByLocation(location);
    }

    @Override
    public List<MyTrip> getTripByDate(String date) {
        return tripsRepository.findAllByStartDate(UtilManager.formatDate(date));
    }

    @Override
    public List<MyTrip> getTripByGender(int gender) {
        return tripsRepository.findAllByUserIdGender(gender);
    }
}
