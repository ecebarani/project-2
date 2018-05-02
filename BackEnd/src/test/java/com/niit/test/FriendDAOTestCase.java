package com.niit.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.FriendDAO;
import com.niit.model.Friend;
import com.niit.model.UserDetails;

public class FriendDAOTestCase 
{

	static FriendDAO friendDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		friendDAO=(FriendDAO)context.getBean("friendDAO");
	}
	
	@Ignore
	@Test
	public void sendFriendTest()
	{
		Friend friend=new Friend();
		friend.setLoginname("rohit");
		friend.setFriendloginname("vinod");
		assertTrue("Problem in Sending Friend Request",friendDAO.sendFriendRequest(friend));
	}
	@Ignore
	@Test
	public void acceptFriendRequest()
	{
		assertTrue("Problem in Accepting Friend Request",friendDAO.acceptFriendRequest(952));
	}
	@Ignore
	@Test
	public void deleteFriendRequest()
	{
		assertTrue("Problem in Deleting Friend Request",friendDAO.deleteFriendRequest(954));
	}
	@Ignore
	@Test
	public void suggestFriendRequest()
	{
		List<UserDetails> listUserDetail=friendDAO.showSuggestedFriend("dolly");
		
		assertTrue("Problem in Listing the Suggested Friends",listUserDetail.size()>0);
		
		for(UserDetails userDetail:listUserDetail)
		{
			System.out.println("Login Name:"+userDetail.getLoginname());
		}
	}
	@Ignore
	@Test
	public void showFriendList()
	{
		List<Friend> listFriend=friendDAO.showFriendList("tarun");
		assertTrue("Problem in Listing Friend",listFriend.size()>0);
		for(Friend friend:listFriend)
		{
			System.out.println("Login Name:"+friend.getLoginname()+"Friend Name:"+friend.getFriendloginname());
		}
	}
	
	@Test
	public void showPendingRequest()
	{
		List<Friend> listFriend=friendDAO.showPendingFriendRequest("tarun");
		
		assertTrue("Problem in Listing Friend",listFriend.size()>0);
		
		for(Friend friend:listFriend)
		{
			System.out.println("Login Name:"+friend.getLoginname()+"Friend Name:"+friend.getFriendloginname());
		}
		
	}
}