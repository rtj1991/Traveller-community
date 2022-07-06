package com.travellers.community.repository;

import com.travellers.community.model.Follower;
import com.travellers.community.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends JpaRepository<Follower,Integer> {
    Follower findByFollowedbyAndAndFollower(User followedby,User follower);
}
