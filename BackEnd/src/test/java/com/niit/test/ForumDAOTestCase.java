package com.niit.test;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.ForumDAO;
import com.niit.model.Forum;
import com.niit.model.ForumComment;

public class ForumDAOTestCase {
static ForumDAO forumDAO;
	
    @BeforeClass
    public static void initialize()
    {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    	context.scan("com.niit");
    	context.refresh();
    	
    	forumDAO=(ForumDAO)context.getBean("forumDAO");	
    }
    
    @Test
    public void addForumTest()
    {
    	Forum forum=new Forum();
    	forum.setForumName("Hibernate Framework");
    	forum.setForumcontent("Blog specific to core java and related concept");
    	forum.setLikes(0);
    	forum.setLoginname("Harish");
    	forum.setStatus("A");
    	forum.setCreateDate(new java.util.Date());
    	
    	assertTrue("problem in Forum Insertion", forumDAO.addForum(forum));
    	
    }
    @Ignore
    @Test
    public void deleteForumTest()
    {
    	Forum forum=forumDAO.getForum(0);
    	assertTrue("Problem in Forum deletion",forumDAO.deleteForum(forum));
    }
    
    @Ignore
    @Test
    public void rejectForumTest()
    {
    	Forum forum=forumDAO.getForum(0);
    	assertTrue("Problem in forum Rejection",forumDAO.rejectForum(forum));
    }
    @Ignore
    @Test
    public void approvalForumTest()
    {
    	Forum forum=forumDAO.getForum(953);
    	assertTrue("Problem in forum Approval",forumDAO.approveForum(forum));
    }
    @Ignore
    @Test
    public void listForumsByUserTest()
    {
    	List<Forum> listForums=forumDAO.listForums("Harish");
    	assertTrue("Problem in listing the Forum",listForums.size()>0);
    	
    	for(Forum forum:listForums)
    	{
    		System.out.print(forum.getForumName()+":::");
    		System.out.print(forum.getForumcontent()+":::");
    		System.out.print(forum.getLoginname()+":::");
    	}
    	
    }
    @Ignore
    @Test
    public void incrementForumLikeTest()
    {
    	Forum forum=forumDAO.getForum(10);
    	assertTrue("Problem in incrementing likes",forumDAO.incrementLikes(forum));
    }
    
    @Ignore
    @Test
    public void addCommentTest()
    {
    	ForumComment comment=new ForumComment();
    	comment.setCommentText("This blog is very nice");
    	comment.setLoginname("Ram");
    	comment.setForumId(953);
    	comment.setCommentDate(new java.util.Date());
    	assertTrue("Problem in insertion of forum Comment ",forumDAO.addForumComment(comment));
    }
    
    @Ignore
    @Test
    public void listAllForumCommentTest()
    {
    	List<ForumComment> listForumComments= forumDAO.listForumComments(953);
    	assertTrue("Problem in retrieving all the ForumComments",listForumComments.size()>0);
    	
    	for(ForumComment forumComment:listForumComments)
    	{
    		System.out.print(forumComment.getForumId()+":::");
    		System.out.print(forumComment.getCommentText()+":::");
    		System.out.print(forumComment.getLoginname());
    	}
    }


}
