package com.niit.test;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogDAO;
import com.niit.dao.JobDAO;
import com.niit.model.Job;

public class JobTestCase {
static JobDAO jobDAO;
	
    @BeforeClass
    public static void initialize()
    {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    	context.scan("com.niit");
    	context.refresh();
      	jobDAO=(JobDAO)context.getBean("jobDAO");
    	
    }
    
    @Test
    public void addTestCase() {
    	Job job=new Job();
    	
    	job.setJobDesignation("Programmer");
    	job.setJobDesc("Need to the coding with diffrent technologies");
    	job.setCompany("BlueSkyInc");
    	job.setLocation("Mumbai");
    	job.setSalary(45000);
    	job.setLastDateApply(new java.util.Date());
    	
    	assertTrue("problem in Adding Job",jobDAO.addJob(job));
    }
    
    @Ignore
    @Test
    public void deleteJobTestCase() {
    	Job job=jobDAO.getJob(952);
    	
    	assertTrue("problem in Deletion",jobDAO.deleteJob(job));
    }
    
    @Ignore
    @Test
    public void listJobs() {
    	List<Job>listJobs=jobDAO.listJobs();
    	assertNotNull("No Jobs",listJobs);
		
		for(Job job:listJobs)
		{
			System.out.print(job.getCompany()+":::");
			System.out.print(job.getJobDesc()+":::");
		}
    	
    }

}