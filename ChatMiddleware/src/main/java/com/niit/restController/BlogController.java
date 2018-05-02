package com.niit.restController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;

@RestController
public class BlogController 
{
	@Autowired
	BlogDAO blogDAO;
	
	@GetMapping("/ShowAllApprovedBlogs")
	public ResponseEntity<List<Blog>> ShowAllApprovedBlogs()
	{
		List<Blog> listBlogs=blogDAO.listBlogs("Harish");
		if(listBlogs!=null)
		{
			return new ResponseEntity <List<Blog>>(listBlogs,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog,HttpSession session)
	{
		blog.setCreateDate(new java.util.Date());
		blog.setLikes(0);
		blog.setLoginname(((Blog) session.getAttribute("userDetail")).getLoginname());
		blog.setStatus("NA");
		
		System.out.println("Blog Name:"+blog.getBlogName());
		System.out.println("Blog content:"+blog.getBlogContent());
		if(blogDAO.addBlog(blog))
		{
			return new ResponseEntity<String>("Blog Added",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/approvedBlog/{blogId}")
	public ResponseEntity<String> approvedBlog(@PathVariable("blogId") int blogId)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogId);
		
		if(blogDAO.approveBlog(blog))
		{
			return new ResponseEntity<String>("Approved",HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity<String>("Error occured",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/rejectBlog/{blogId}")
	public ResponseEntity<String> rejectBlog(@PathVariable("blogId") int blogId)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogId);
		
		if(blogDAO.rejectBlog(blog))
		{
			return new ResponseEntity<String>("Reject",HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity<String>("Error occured",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/deleteBlog/{blogId}")
	public ResponseEntity<String> deleteBlog(@PathVariable("blogId") int blogId)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogId);
		
		if(blogDAO.deleteBlog(blog))
		{
			return new ResponseEntity<String>("Delete",HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity<String>("Error",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/incrementLike/{blogId}")
	public ResponseEntity<String> incrementLike(@PathVariable("blogId") int blogId)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogId);
		
		if(blogDAO.incrementLike(blog))
			return new ResponseEntity<String>("Incremented",HttpStatus.OK);
		
		else
			return new ResponseEntity<String>("Error occured",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("getABlog/{blogId}")
	public ResponseEntity<Blog> getABlog(@PathVariable("blogId") int blogId)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogId);
		
		if(blog!=null)
		{
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		}
	}	

}
