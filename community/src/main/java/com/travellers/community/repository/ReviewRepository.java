package com.travellers.community.repository;

import com.travellers.community.model.MyTrip;
import com.travellers.community.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    List<Review> findAllByReviewer(MyTrip myTrip);

}
