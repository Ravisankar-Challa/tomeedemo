package com.example.service;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.example.entity.Member;
import com.example.repository.MemberRespository;

/**
 * @author Ravisankar C
 *
 */
@Startup
@Singleton(name="MemberStartupService")
public class StartupService {
	
	@Inject
	private MemberRespository repository;
	
	@PostConstruct
	public void init() {
		Member m = new Member();
		m.setName("Ravi");
		repository.addMember(m);
		m = new Member();
		m.setName("Sankar");
		repository.addMember(m);
		m = new Member();
		m.setName("Challa");
		repository.addMember(m);
	}
	
}
