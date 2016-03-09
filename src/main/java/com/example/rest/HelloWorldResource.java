package com.example.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
//import javax.cache.annotation.CacheKey;
//import javax.cache.annotation.CacheResult;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.tomitribe.sabot.Config;

import com.example.entity.Member;
import com.example.repository.MemberRespository;

/**
 * @author Ravisankar C
 *
 */
@Path("/")
@Singleton
@Lock(LockType.READ)
public class HelloWorldResource {
	
	private static final Logger LOG = Logger.getLogger(HelloWorldResource.class.getName());

	@Inject
	@Config("name")
	private String name;
	
	@Inject
	private MemberRespository repository;
	
	public HelloWorldResource() {
		LOG.info("Creating Hello World Resource Object");
	}
	
	@PostConstruct
	public void init() {
		LOG.info("Inside Hello World Resource @Post Construct");
	}
	
	@GET
	@Path("hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return String.format("Hello World %s", name);
	}
	
	@GET
	@Path("member/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	//@CacheResult(cacheName="testCache")
	public Member findMember(@PathParam("id") String id) {
		System.out.println(id+" number");
		return repository.findMember(Long.valueOf(id));
	}
	
	@GET
	@Path("members")
	//@CacheResult(cacheName="testCache")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Member> findAll() {
		return repository.findAll();
	}
	
	@PreDestroy
	public void destroy() {
		LOG.info("Inside Hello World Resource @PreDestroy");
	}
}
 