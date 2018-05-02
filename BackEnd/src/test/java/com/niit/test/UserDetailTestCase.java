package com.niit.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.UserDAO;
import com.niit.model.UserDetails;


public class UserDetailTestCase {
	static UserDAO userDAO;
	
	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		userDAO=(UserDAO)context.getBean("userDAO");
	}

	@Test
	public void addUserTest()
	{
		UserDetails user=new UserDetails();
		user.setMobileNo("9600786906");
		user.setPassword("user");
		user.setUsername("user");
		assertTrue("Problem in User Insertion",userDAO.addUser(user));
	}
    
	@Test
	public void updateUserTest()
	{
		UserDetails user=userDAO.getUser(1);
	    user.setUsername("The Admin");
		assertTrue("Problem in Updation",userDAO.updateUser(user));
	}
	
	
	@Test
	public void listUserTest()
	{
		List<UserDetails> listUser=userDAO.listUser("");
		assertNotNull("No User",listUser);
		
		for(UserDetails user:listUser)
		{
			System.out.print(user.getUsername()+":::");
			
		}
	}

}
