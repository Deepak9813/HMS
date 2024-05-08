package com.dpk.HospitalManagementSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dpk.HospitalManagementSystem.model.User;
import com.dpk.HospitalManagementSystem.repository.UserRepository;
import com.dpk.HospitalManagementSystem.service.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User userSignup(User user) {

		// ============ Encrypt Password ======================
		// String password = passwordEncoder.encode(user.getPassword());
		// user.setPassword(password);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// =========== Set Role [Set Role of User] ============
		user.setRole("ROLE_USER");

		// return userRepo.save(user);
		User newUser = userRepo.save(user);
		return newUser;
	}

	@Override
	public User adminSignup(User user) {

		// ============ Encrypt Password ======================
		// String password = passwordEncoder.encode(user.getPassword());
		// user.setPassword(password);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// =========== Set Role [Set Role of Admin] ============
		user.setRole("ROLE_ADMIN");

		// return userRepo.save(user);
		User newAdmin = userRepo.save(user);
		return newAdmin;
	}

	@Override
	public boolean deleteUser(Long id) {

		User user = userRepo.findById(id).get();
		if (user != null) {

			userRepo.delete(user);
			// userRepo.deleteById(id);

			return true;
		}

		return false;
	}

	@Override
	public User updateUser(User user) {

		// ============ Encrypt Password ======================
		// String password = passwordEncoder.encode(user.getPassword());
		// user.setPassword(password);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// return userRepo.save(user);
		User updateUser = userRepo.save(user);
		return updateUser;
	}

	@Override
	public User getUserById(Long id) {

		return userRepo.findById(id).get();
	}

	@Override
	public List<User> getAllUsers() {

		return userRepo.findAll();
	}

	@Override
	public User getUserByEmail(String email) {

		return userRepo.findByEmail(email);
	}

	@Override
	public void removeSessionMessage() {

		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg"); // in ("key", value) , write key

	}

}
