package com.travellers.community.repository;

import com.travellers.community.model.MyTrip;
import com.travellers.community.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyTripsRepository extends JpaRepository<MyTrip,Integer> {
//    List<MyTrip> findAllByUserIdWithinIgnoreCase(User user);
    List<MyTrip> findAllByUserIdNot(User user);
}
