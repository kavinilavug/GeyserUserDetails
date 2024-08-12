package com.geyser.userinfo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.geyser.userinfo.customexception.BusinessException;
import com.geyser.userinfo.entity.UserDetails;
import com.geyser.userinfo.repository.UserRepository;

@Component
public class UserDetailsService {

	@Autowired
	private UserRepository userRepository;
<<<<<<< HEAD

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

	public UserDetails selectUserId(Long userid) {
		//If its not there in table it will go to the else throw part
		return userRepository.findById(userid)
				.orElseThrow(() -> new BusinessException(false, "No value is present in DB for userid"));
	}

	public String deleteUserById(Long userid) {
		userRepository.findById(userid).orElseThrow(()->new BusinessException(false, "userid is not present in the table ,So unable to find and delete"));
		userRepository.deleteById(userid);
		return "succesfully deleted";

	}

	public UserDetails updateUserById(Long userid, UserDetails userDetails) {
	    userRepository.findById(userid).orElseThrow(()->new BusinessException(false, "userid is not present in the table to update it"));
	    
		UserDetails	updateUser = userRepository.save(userDetails);
		return updateUser;
=======
	public UserDetails saveUser(UserDetails userDetails) {
		
		if (userDetails.getUsername().isEmpty() || userDetails.getUsername().length() == 0) {
			throw new BusinessException("601", "Please send properName,It is blank");
		}
		try {
			UserDetails userDataSaved = userRepository.save(userDetails);
			return userDataSaved;
		} catch (IllegalArgumentException e) {
			// when i am facing IllegalArgumentException ,my business means custom exception
			// should be work
			// fornt end people should find only user name getting error or full hole user
			// object is null
			throw new BusinessException("602", "given user is null" + e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("603",
					"Something went wrong in service layer while saving the empyloee" + e.getMessage());
		}
	}

	public List<UserDetails> selectAllUser() {
		List<UserDetails> userDataSaved;
		try {
			userDataSaved = userRepository.findAll();
		} catch (Exception e) {
			throw new BusinessException("604",
					"Something went wrong in service layer while saving fecting the employee" + e.getMessage());
		}
		if (userDataSaved.isEmpty()) {
			throw new BusinessException("605", "Hole List is empty ,we dnt have data in database");
		}
		return userDataSaved;
	}

	public UserDetails selectUserId(Long userid) {

		try {
			return userRepository.findById(userid).get();
		} catch (IllegalArgumentException e) {
			throw new BusinessException("606", "given user id is null,please some id to be search" + e.getMessage());
		} catch (NoSuchElementException e) {
			throw new BusinessException("607", "given user id doesnot exit in database" + e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("610",
					"Something went wrong in service layer while deleting the specific user id" + e.getMessage());
		}
	}

	public String deleteUserById(Long userid) {
		try {
				userRepository.deleteById(userid);
				return "succesfully deleted";
		} catch (IllegalArgumentException e) {
			throw new BusinessException("608", "given user id is null,please some id to be search" + e.getMessage());
		} catch (NoSuchElementException e) {
			throw new BusinessException("609", "given user id doesnot exit in database" + e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("610",
					"Something went wrong in service layer while deleting the specific user id" + e.getMessage());
		}
	}

	public UserDetails updateUserById(Long userid, UserDetails userDetails) {
		UserDetails updateUser = null;
		try {
			Optional<UserDetails> userDetailsData = userRepository.findById(userid);
			if (userDetailsData.isPresent()) {
				UserDetails userDetailsUpdate = userDetailsData.get();
				userDetailsUpdate.setUsername(userDetails.getUsername());
				userDetailsUpdate.setPassword(userDetails.getPassword());
				userDetailsUpdate.setEmail(userDetails.getEmail());
				updateUser = userRepository.save(userDetailsUpdate);
			}
			return updateUser;
		} catch (IllegalArgumentException e) {
			throw new BusinessException("611", "given user id is null,please some id to be search" + e.getMessage());
		} catch (NoSuchElementException e) {
			throw new BusinessException("612", "given user id doesnot exit in database" + e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("613",
					"Something went wrong in service layer while updating the specific user" + e.getMessage());
		}
>>>>>>> branch 'main' of https://github.com/kavinilavug/GeyserUserDetails.git
	}
}
