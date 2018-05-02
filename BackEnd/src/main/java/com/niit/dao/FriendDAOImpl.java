package com.niit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Friend;
import com.niit.model.UserDetails;

public class FriendDAOImpl implements FriendDAO 
{

	@Autowired
	SessionFactory sessionFactory;
	
	
	public List<Friend> showFriendList(String loginname) 
	{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from Friend where (loginname=:myloginname or friendloginname=:friendlogin) and status='A'");
			query.setParameter("myloginname",loginname);
			query.setParameter("friendlogin", loginname);
			List<Friend> listFriends=(List<Friend>)query.list();
			return listFriends;	
	}

	
	public List<Friend> showPendingFriendRequest(String loginname) 
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where friendloginname=:myloginname and status='P'");
		query.setParameter("myloginname",loginname);
		List<Friend> listFriends=(List<Friend>)query.list();
		return listFriends;
	}


	public List<UserDetails> showSuggestedFriend(String loginname) 
	{
		Session session=sessionFactory.openSession();
		SQLQuery sqlQuery=session.createSQLQuery("select loginname from UserDetail where loginname not in(select friendloginname from Friend where loginname='"+loginname+"') and loginname!='"+loginname+"'");
		List<String> listUsers=(List<String>)sqlQuery.list();
		ArrayList<UserDetails> listUserDetail=new ArrayList<UserDetails>();
		int i=0;
		while(i<listUsers.size())
		{
			UserDetails userDetail=session.get(UserDetails.class, listUsers.get(i));
			listUserDetail.add(userDetail);
			i++;
		}
		
		return listUserDetail;
	}

	@Transactional

	public boolean sendFriendRequest(Friend friend) 
	{
		try
		{
			friend.setStatus("P");
			sessionFactory.getCurrentSession().save(friend);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Transactional
	public boolean acceptFriendRequest(int friendId) 
	{
		try
		{
			Session session=sessionFactory.openSession();
			Friend friend=session.get(Friend.class, friendId);
			System.out.println("Login Name"+friend.getLoginname());
			friend.setStatus("A");
			session.update(friend);
			System.out.println("Updated");
			session.flush();
			session.close();
			return true;
		}
		catch(Exception e)
		{
			return false;	
		}
	}
	
	@Transactional	
	public boolean deleteFriendRequest(int friendId) {
		try
		{
			Session session=sessionFactory.openSession();
			Friend friend=session.get(Friend.class, friendId);
			session.delete(friend);
			session.flush();
			session.close();
			return true;
		}
		catch(Exception e)
		{
			return false;	
		}
	}

}