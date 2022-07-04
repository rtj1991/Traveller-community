package com.travellers.community.repository;

import com.travellers.community.model.MyTrips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyTripsRepository extends JpaRepository<MyTrips,Integer> {
}
