package com.example.rest;

import java.util.List;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;
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
public class HelloWorldResource {

	@Inject
	@Config("name")
	private String name;
	
	@Inject
	private MemberRespository repository;
	
	@GET
	@Path("hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return String.format("Hello World!!! %s", name);
	}
	
	@GET
	@Path("member/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CacheResult(cacheName="testCache")
	public Member findMember(@PathParam("id") @CacheKey String id) {
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
}
 