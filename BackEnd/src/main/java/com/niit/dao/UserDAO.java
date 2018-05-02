package com.niit.dao;

import java.util.List;

import com.niit.model.UserDetails;

public interface UserDAO {
	public boolean addUser(UserDetails userDetails);
	public UserDetails getUser(int userId);
	public boolean deleteUser(UserDetails userDetails);
	public boolean updateUser(UserDetails userDetails);
	public List<UserDetails> listUser(String username);


}
