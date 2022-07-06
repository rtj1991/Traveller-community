package com.travellers.community.repository;

import com.travellers.community.model.MyTrip;
import com.travellers.community.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MyTripsRepository extends JpaRepository<MyTrip, Integer> {
    List<MyTrip> findAllByUserIdNot(User user);

    List<MyTrip> findAllByLocationAndStartDateAndUserIdGender(String location, Date date, int gender);
    List<MyTrip> findAllByLocationAndStartDate(String location, Date date);
    List<MyTrip> findAllByStartDateAndUserIdGender(Date date,int gender);
    List<MyTrip> findAllByLocationAndUserIdGender(String location,int gender);
    List<MyTrip> findAllByLocation(String location);

    List<MyTrip> findAllByStartDate(Date date);
    List<MyTrip> findAllByUserIdGender(int gender);
}
