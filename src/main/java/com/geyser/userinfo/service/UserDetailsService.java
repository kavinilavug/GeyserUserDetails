package com.geyser.userinfo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import com.geyser.userinfo.customexception.BusinessException;
import com.geyser.userinfo.entity.UserDetails;
import com.geyser.userinfo.repository.UserRepository;

@Component
public class UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public UserDetails saveUser(UserDetails userDetails) {
		UserDetails userDataSaved = userRepository.save(userDetails);
		return userDataSaved;
	}

	public List<UserDetails> selectAllUser() {
		List<UserDetails> userDataSaved = userRepository.findAll();
		if (userDataSaved.isEmpty()) {
			// Handle the empty list case ,For example, you might want to throw an exception or return an empty list
			throw new BusinessException(false, "No employees found");
		}
		return userDataSaved;
	}

	@Cacheable(cacheNames = "UserDetails", key = "#p0")
	public UserDetails selectUserId(Long userid) {
		//If its not there in table it will go to the else throw part
		return userRepository.findById(userid)
				.orElseThrow(() -> new BusinessException(false, "No value is present in DB for userid"));
	}


	@CacheEvict(cacheNames = "UserDetails", key = "#p0")
	public String deleteUserById(Long userid) {
		userRepository.findById(userid).orElseThrow(()->new BusinessException(false, "userid is not present in the table ,So unable to find and delete"));
		userRepository.deleteById(userid);
		return "succesfully deleted";

	}


	@CachePut(cacheNames = "UserDetails", key = "#p0")
	public UserDetails updateUserById(Long userid, UserDetails userDetails) {
	    userRepository.findById(userid).orElseThrow(()->new BusinessException(false, "userid is not present in the table to update it"));
	    
		UserDetails	updateUser = userRepository.save(userDetails);
		return updateUser;
	}
}
