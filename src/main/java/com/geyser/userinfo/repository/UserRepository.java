package com.geyser.userinfo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geyser.userinfo.entity.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {

	Optional<UserDetails> findById(Long userid);

	Object deleteById(int userid);
}