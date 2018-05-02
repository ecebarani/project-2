package com.niit.dao;

import java.util.List;

import com.niit.model.Friend;
import com.niit.model.UserDetails;

public interface FriendDAO 
{
	public List<Friend> showFriendList(String loginname);
	public List<Friend> showPendingFriendRequest(String loginname);
	public List<UserDetails> showSuggestedFriend(String loginname);
	
	public boolean sendFriendRequest(Friend friend);
	public boolean acceptFriendRequest(int friendId);
	public boolean deleteFriendRequest(int friendId);
}